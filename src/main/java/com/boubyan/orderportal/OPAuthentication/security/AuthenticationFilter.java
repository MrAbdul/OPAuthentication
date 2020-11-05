//package com.boubyan.orderportal.OPAuthentication.security;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.boubyan.orderportal.OPAuthentication.models.UserLogin;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//	private final AuthenticationManager authenticationManager;
//
//	@Value("${jwt.secret}")
//	private  String secret;
//	
//	//change private to public (check)
//	public AuthenticationFilter(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest req, 
//			HttpServletResponse res) throws AuthenticationException {
//		
//		try {
//
//			UserLogin creds = new ObjectMapper()
//					.readValue(req.getInputStream(), UserLogin.class);
//			
//			return authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(
//							creds.getEmail(), 
//							creds.getPassword(), 
//							new ArrayList<>()));
//
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	
//	@Override
//	protected void successfulAuthentication(HttpServletRequest req,
//			HttpServletResponse res,
//			FilterChain chain,
//			Authentication auth) throws IOException, ServletException {
//		
//		String userName = ((User) auth.getPrincipal()).getUsername();
//		
//		String token = Jwts.builder()
//				.setSubject(userName)
//				.claim("userId", )
//				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//				.signWith(SignatureAlgorithm.HS512,secret )
//				.compact();
//		
//		
//		
//		// header information
//		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);	
//	}
//	
//}
