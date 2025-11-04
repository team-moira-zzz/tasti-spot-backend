package com.example.tastispotbackend.global.auth;

import com.example.tastispotbackend.global.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import static com.example.tastispotbackend.global.constant.TastiSpotConstant.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration-time.atk}")
    private Long expirationTimeOfAtk;

    @Value("${jwt.expiration-time.rtk}")
    private Long expirationTimeOfRtk;


    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(secretKey)
        );
    }

    private String createToken(User user, Long expirationTime) {
        // claim
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_NAME_EMAIL, user.getEmail());
        claims.put(CLAIM_NAME_ROLE, user.getRole());
        claims.put(CLAIM_NAME_NICKNAME, user.getNickname());

        // time
        Date now = new Date();

        // create token
        return Jwts.builder()
                .subject(user.getId())
                .claims(claims)
                .expiration(new Date(now.getTime() + expirationTime))
                .issuedAt(now)
                .issuer(issuer)
                .signWith(key)
                .compact();
    }

    private String createAtk(User user) {
        return createToken(user, expirationTimeOfAtk);
    }

    private String createRtk(User user) {
        return createToken(user, expirationTimeOfRtk);
    }

    public TokenResponse createTokens(User user) {
        String atk = this.createAtk(user);
        String rtk = this.createRtk(user);

        return new TokenResponse(atk, rtk);
    }

    public String substringToken(String authorizationHeaderValue) {
        return authorizationHeaderValue.substring(BEARER_PREFIX.length());
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}