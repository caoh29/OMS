package caoh29.OMS.auth_server.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private SecretKey secretKey = null;


    private SecretKey getSignKey() {
        if (this.secretKey != null) {
            return this.secretKey;
        }

        if(this.secret == null || this.secret.isEmpty() || this.secret.isBlank()) {
            this.secretKey = Jwts.SIG.HS256.key().build();
            return this.secretKey;
        }

        if(this.secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }

        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        return this.secretKey;
    }

    public Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private <T> T getClaim(String token, Function<Claims, T> resolver) {
        Claims claims = this.getAllClaims(token);
        return resolver.apply(claims);
    }

    public String getUsernameClaim(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isValidToken(String token) {
        return Jwts.parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .after(new Date(System.currentTimeMillis()));
    }

    public boolean isVerifiedToken(String token, UserDetails userDetails) {
        final String username = this.getClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername()) && this.isValidToken(token));
    }

    public String generateToken(Claims claims, String content) {
        return Jwts.builder()
                .claims(claims)
                .content(content)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.jwtExpiration * 1000L))
                .encodePayload(true)
                .signWith(this.getSignKey())
                .compact();
    }

    public String generateTokenWithUsername(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.jwtExpiration * 1000L))
                .encodePayload(true)
                .signWith(this.getSignKey())
                .compact();
    }
}
