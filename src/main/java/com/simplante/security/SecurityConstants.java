package com.simplante.security;


public class SecurityConstants {

    /*    @Value("${jwt.secret}")*/
    public static final String SECRET = "H1A2S3H4";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
