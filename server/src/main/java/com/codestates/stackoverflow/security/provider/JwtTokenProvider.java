package com.codestates.stackoverflow.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;


    //secretKey 를 Base64로 인코딩
    @PostConstruct
    protected void init() {
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

//    일단 refreshToken 은 사용 안하는것으로.. 추후에 추가
//    public String createRefreshToken(String username) {
//        return Jwts
//                .builder()
//                .setSubject(username)
//                .setIssuedAt(Calendar.getInstance().getTime())
//                .setExpiration(refreshTokenExpirationTime)
//                .signWith(getSecretKeySignedHS256(secretKey))
//                .compact();
//    }

    private Key getSecretKeySignedHS256(String secretKey) {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);
        return key;
    }
}
