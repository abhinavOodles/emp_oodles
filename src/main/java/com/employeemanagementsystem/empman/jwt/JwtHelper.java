package com.employeemanagementsystem.empman.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY  = 5 * 60 * 60  ;  // This is 5 hr / 60 min / 60 sec
    private final String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf" ;

    //getting username from token
    public String getUsernameFromToken (String token){
        return getClaimFromToken(token , Claims::getSubject) ;
    }
    // getting password from token
    public Date getExpirationsDateFromToken (String  token){
        return getClaimFromToken (token , Claims::getExpiration)   ;
    }

    private <T>  T getClaimFromToken(String token, Function<Claims , T> claimsR) {
        final  Claims claims = getAllClaimsFromToken(token) ;
        return claimsR.apply(claims) ;
    }

    //getting all the available information from project
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody() ;
    }

    //checking expiry of token
    private Boolean isTokenExpired (String token){
        final Date expiration = getExpirationsDateFromToken(token) ;
        return expiration.before(new Date()) ;
    }

    //generate token from user
    public String generateToken (UserDetails userDetails){
        Map<String , Object> claims = new HashMap<>() ;
        return doGenerateToken(claims , userDetails.getUsername()) ;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))  // multiplication for converting into millisecnod
                .signWith(SignatureAlgorithm.HS512  , secret)
                .compact() ;
    }

    //validation of token
    public Boolean validateToken (String token , UserDetails userDetails){
        final String username = getUsernameFromToken(token) ;
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) ;
    }

}
