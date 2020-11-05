package com.boubyan.orderportal.OPAuthentication.utils;

import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Base64;
import java.util.Date;


import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

//	static final String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";


	/**
	 * 
	 */
	private static final long serialVersionUID = 8345416179241915785L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private  String secret;
	

	public  String createJWT(String id, String name, String email,String role) {
//		logger.info(secret);
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());

		Instant now = Instant.now();
		String jwtToken = Jwts.builder().claim("name", name).claim("email", email).claim("id", id).claim("role", role)
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(5, ChronoUnit.HOURS))).signWith(SignatureAlgorithm.HS256, hmacKey)
				.compact();

		return jwtToken;
	}
	
	public  Jws<Claims> parseJwt(String jwtString) {
		
		String secretCode = secret;
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretCode), SignatureAlgorithm.HS256.getJcaName());

		Jws<Claims> jwt = Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(jwtString);

		return jwt;
	}

//	// retrieve username from jwt token
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//
//	// retrieve expiration date from jwt token
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//
//	//genaric method
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//
//	// for retrieveing any information from token we will need the secret key
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	}
//
//	// check if the token has expired
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	// generate token for user
//	public String generateToken(User userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		return doGenerateToken(claims, userDetails.getEmail());
//	}
//
//	// while creating the token -
//	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
//	// 2. Sign the JWT using the HS512 algorithm and secret key.
//	// 3. According to JWS Compact
//	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//	// compaction of the JWT to a URL-safe string
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//				.signWith(SignatureAlgorithm.HS512, secret).compact();
//	}
//
//	// validate token
//	public Boolean validateToken(String token, User userDetails) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(userDetails.getEmail()) && !isTokenExpired(token));
//	}

}
