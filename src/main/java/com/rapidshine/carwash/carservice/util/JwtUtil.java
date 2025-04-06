package com.rapidshine.carwash.carservice.util;

import com.rapidshine.carwash.carservice.exceptions.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long EXPIRATION_TIME = 1000*60*60;

    public JwtUtil(@Value("${jwt.secret}") String key){
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

    }

    public Claims extractClaims(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException ex){
            throw  new InvalidJwtTokenException("Token has expired");
        }catch (MalformedJwtException ex){
            throw  new InvalidJwtTokenException("Invalid JWT Token!");
        } catch (Exception e) {
            throw new InvalidJwtTokenException("JWT Token error : "+e.getMessage());
        }
    }

    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token){
        return extractClaims(token).get("role",String.class);
    }
    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }


    // here I have validated the role if it is customer is not because car can only be accessed by the customer
    public boolean validateToken(String token,String email){
        return (email.equals(extractEmail(token)) && extractRole(token).equals("CUSTOMER") && !isTokenExpired(token));
    }

}
