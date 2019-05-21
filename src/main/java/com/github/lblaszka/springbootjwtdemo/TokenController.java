package com.github.lblaszka.springbootjwtdemo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


@RestController
public class TokenController
{
    @PostMapping("getToken")
    public ResponseEntity getToken( @RequestBody Login login )
    {
        Long now = System.currentTimeMillis();

        if( login.getUsername().equals( "user" ) && login.getPassword().equals( "user" ) )
        {
            String token = Jwts.builder()
                    .setSubject( login.getUsername() )
                    .claim( "role", Arrays.asList( "ROLE_USER" ) )
                    .setIssuedAt( new Date(now) )
                    .setExpiration( new Date(now + 3600000 ) ) // 1h
                    .signWith( SignatureAlgorithm.HS256, PrivateDemoKey.PRIVATE_KEY )
                    .compact();
            return ResponseEntity.ok( token );
        }

        if( login.getUsername().equals( "admin" ) && login.getPassword().equals( "admin" ) )
        {
            String token = Jwts.builder()
                    .setSubject( login.getUsername() )
                    .claim( "role", Arrays.asList("ROLE_USER","ROLE_ADMIN") )
                    .setIssuedAt( new Date(now) )
                    .setExpiration( new Date(now + 3600000 ) ) // 1h
                    .signWith( SignatureAlgorithm.HS256, PrivateDemoKey.PRIVATE_KEY )
                    .compact();
            return ResponseEntity.ok( token );
        }

        return ResponseEntity.badRequest().body( "Fail login!" );
    }
}
