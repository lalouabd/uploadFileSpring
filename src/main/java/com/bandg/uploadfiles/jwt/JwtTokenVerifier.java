package com.bandg.uploadfiles.jwt;

import com.bandg.uploadfiles.configuration.JwtConfig;
import com.bandg.uploadfiles.service.PersonService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {


    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    private  final PersonService personService;

    public JwtTokenVerifier(SecretKey secretKey,
                            JwtConfig jwtConfig, PersonService personService) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.personService = personService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

            String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

            if (authorizationHeader == null ||  !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

            try {

                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token);

                Claims body = claimsJws.getBody();

                String username = body.getSubject();


                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,null
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JwtException e) {
                throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
            }

            filterChain.doFilter(request, response);
    }
}
