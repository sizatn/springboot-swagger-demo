package com.sizatn.ssd.utils;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

	private final static String secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

	// 过期时间1小时
	private static final long EXPIRE_TIME = 1 * 60 * 60 * 1000;

	/**
	 * @param token
	 * @param username
	 * @return 是否正确
	 * @desc 校验token是否正确
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	@SuppressWarnings("unused")
	public static boolean verify(String token, String username) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param token
	 * @return token中包含的claim信息
	 * @desc 获得token中的信息无需secret解密也能获得
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static Map<String, Claim> getClaims(String token) {
		try {
			return JWT.decode(token).getClaims();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * @param username 用户名
	 * @return 加密的token
	 * @desc 生成签名，1小时后过期
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static String create(String username) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			// 附带username信息
			return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

}
