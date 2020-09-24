package com.fatec.mogi.auth;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTH_CLAIM = "AUTH";
	public static final String HEADER_STRING = "Authorization";
	public static final String PERMISSION_STRING = "Permision";
	public static final String SIGN_UP_URL = "/users/sign-up";

}
