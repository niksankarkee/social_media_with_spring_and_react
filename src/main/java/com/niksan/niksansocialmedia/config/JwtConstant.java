package com.niksan.niksansocialmedia.config;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtConstant {
    public static String JWT_HEADER = "Authorization";
    public static String SECRET_KEY = "randomstridsafsdafasdfasdfasdfsadfsadfsdafasdfsadfsafsdfng";

    public static SecretKey KEY = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
}
