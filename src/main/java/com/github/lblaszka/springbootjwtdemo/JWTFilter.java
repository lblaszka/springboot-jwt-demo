package com.github.lblaszka.springbootjwtdemo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

public class JWTFilter implements Filter
{
    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("authorization");

        if( header != null )
        {
            if( header.startsWith("Bearer ") )
            {
                try
                {
                    Claims claims = Jwts.parser().setSigningKey( PrivateDemoKey.PRIVATE_KEY ).parseClaimsJws( header.substring(7) ).getBody();
                    httpServletRequest.setAttribute( "claims", claims );

                    LinkedList<GrantedAuthority> grantedAuthorityLinkedList = new LinkedList<>();

                    for( String authority : ( Collection<String >)claims.get("role") )
                    {
                        grantedAuthorityLinkedList.add( new SimpleGrantedAuthority( authority ) );
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, grantedAuthorityLinkedList );
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
                catch( UnsupportedJwtException exception )
                {
                    System.err.println("JWTFilter UnsupportedJwtException!");
                }
                catch( Exception exception )
                {

                }
            }
        }

        filterChain.doFilter( httpServletRequest, servletResponse );
    }
}
