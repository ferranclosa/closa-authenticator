package com.closa.global.functions;

import com.closa.global.security.model.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.security.InvalidKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    
    public static final long JWT_TOKEN_VALIDITY = SecurityConstants.EXPIRATION_TIME;

    private Boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }
    public Date  extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME * 10))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }



        public String getUsernameFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }

        public Date getIssuedAtDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getIssuedAt);
        }

        public Date getExpirationDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }

        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }

        private Claims getAllClaimsFromToken(String token) {


            return Jwts
                    .parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }


        private Boolean ignoreTokenExpiration(String token) {
            // here you specify tokens, for that the expiration is ignored
            return false;
        }

        public String generateToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return doGenerateToken(claims, userDetails.getUsername());
        }

        private String doGenerateToken(Map<String, Object> claims, String subject) {

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)

                    .compact();
        }

        public Boolean canTokenBeRefreshed(String token) {
            return (!isTokenExpired(token) || ignoreTokenExpiration(token));
        }


}
