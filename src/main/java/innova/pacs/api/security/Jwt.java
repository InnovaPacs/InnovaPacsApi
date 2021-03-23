package innova.pacs.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Jwt {
	private static final String KEY = "Demo";
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
		        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
		        .signWith(SignatureAlgorithm.HS256, KEY).compact();
	}
	
	public Boolean validateToken(String jwt, UserDetails userDetails) {
		return userDetails.getUsername().equals(this.extractUsername(jwt));
	}
	
	public String extractUsername(String jwt) {
		return this.getClaims(jwt).getSubject();
	} 
	
	public Boolean isJwtExpired(String jwt) {
		return this.getClaims(jwt).getExpiration().before(new Date());	
	}
	
	public Claims getClaims(String jwt) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
	}
}
