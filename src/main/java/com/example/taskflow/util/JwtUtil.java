package com.example.taskflow.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private   static  final String SECRET_KEY_STRING="mySuperSecretKeyForJwtTokenGeneration123!";
    private  final Key SECRECT_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(String email){
        String token = String.valueOf(Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+86400000)).signWith(SECRECT_KEY).compact());
        System.out.println("Genrate token "+token );

        return  token;
    }

    public  String extractEmail(String token){
        return Jwts.parserBuilder().setSigningKey(SECRECT_KEY).build().parseClaimsJws(token).getBody().getSubject();
    }

    public  boolean validateToken(String Token){
        try{
            extractEmail(Token);

            System.out.println("Token valid hai");
            return  true;
        }
        catch (Exception e){
            System.out.println("Token invalid: " + e.getMessage());
            return  false;

        }
    }


}
