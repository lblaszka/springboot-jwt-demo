package com.github.lblaszka.springbootjwtdemo;

import io.jsonwebtoken.Claims;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("demo")
public class DemoController
{
    @GetMapping("/forAll")
    public String forAll()
    {
        return "It is for all!";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/forUser")
    public String forUser()
    {
        return "It is for user!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/forAdmin")
    public String forAdmin()
    {
        return "It is for admin!";
    }

    @GetMapping("/me")
    public String me( @RequestAttribute( name = "claims", required = false ) Claims claims )
    {
        if( claims == null )
            return "You are nothing!";

        return new StringBuilder(  )
                .append( "Username:" )
                .append( claims.getSubject() )
                .append( ", Role: " )
                .append( claims.get("role") )
                .toString();
    }
}
