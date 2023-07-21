package com.example.storesettlement.services;

import com.example.storesettlement.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.access-token.expiration}")
    private long ACCESS_EXPIRATION;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_EXPIRATION;


    @Transactional
    public String generateAccessToken(Member member) {
        return generateAccessToken(new HashMap<>(), member);
    }

    @Transactional
    public String generateAccessToken(
            Map<String, Object> extraClaims,
            Member member
    ) {
        extraClaims.put("role", member.getRole().name());
        return buildToken(extraClaims, member, ACCESS_EXPIRATION);
    }

    @Transactional
    public String generateRefreshToken(
            Member member
    ) {
        return buildToken(new HashMap<>(), member, REFRESH_EXPIRATION);
    }

    @Transactional
    private String buildToken(
            Map<String, Object> extraClaims,
            Member member,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(member.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Transactional
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Transactional
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Transactional
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Transactional
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Transactional
    public String extractRole(String token) { return (String) extractAllClaims(token).get("role");}

    @Transactional
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Transactional
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Transactional
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
