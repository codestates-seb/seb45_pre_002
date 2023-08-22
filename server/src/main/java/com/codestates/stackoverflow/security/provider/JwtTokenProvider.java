package com.codestates.stackoverflow.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    private final Map<String, Long> usedTokenMap = new HashMap<>();

    @PostConstruct
    protected void init() {
        //secretKey 를 Base64로 인코딩
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public String createAccessToken(Map<String, Object> claims, String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date accessTokenExpirationTime = calendar.getTime();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(accessTokenExpirationTime)
                .signWith(getSecretKeySignedHS256(secretKey))
                .compact();
    }

    private Key getSecretKeySignedHS256(String secretKey) {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);
        return key;
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedSecretKey();

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

        return claims;
    }

    public Key getKeyFromBase64EncodedSecretKey() {
        byte[] decodedKey = Decoders.BASE64.decode(secretKey);

        Key secretKey = Keys.hmacShaKeyFor(decodedKey);

        return secretKey;
    }

    public boolean isTokenInBlackList(String jws) {
        if (!usedTokenMap.containsKey(jws)) {
            return false;
        }

        long expirationTime = usedTokenMap.get(jws);

        if (System.currentTimeMillis() < expirationTime) {
            usedTokenMap.remove(jws);
            return false;
        }

        return true;
    }

    public void addToUsedToken(String jws) {
        byte[] decodedKey = Decoders.BASE64.decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);
        long expirationTime = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getExpiration().getTime();

        usedTokenMap.put(jws, expirationTime);
    }
}
