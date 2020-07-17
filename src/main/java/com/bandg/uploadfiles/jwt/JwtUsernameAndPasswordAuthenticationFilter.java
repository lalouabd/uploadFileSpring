package com.bandg.uploadfiles.jwt;

import com.bandg.uploadfiles.configuration.JwtConfig;
import org.springframework.security.authentication.AuthenticationManager;

import javax.crypto.SecretKey;
import javax.servlet.*;
import java.io.IOException;

public class JwtUsernameAndPasswordAuthenticationFilter implements Filter {

    public JwtUsernameAndPasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig,
            SecretKey secretKey) {
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {


    }
}
