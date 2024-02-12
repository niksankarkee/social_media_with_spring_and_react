package com.niksan.niksansocialmedia.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.niksan.niksansocialmedia.config.JwtConstant.KEY;

public class JwtProvider {



    public static String generateToken(Authentication auth){

        String jwt = Jwts.builder().setIssuer("niksan")
                                    .setIssuedAt( new Date())
                                    .setExpiration(new Date(new Date().getTime() + 86400000))
                                    .claim("email", auth.getName())
                                    .signWith(KEY)
                                    .compact();

        return  jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));

    }
}
