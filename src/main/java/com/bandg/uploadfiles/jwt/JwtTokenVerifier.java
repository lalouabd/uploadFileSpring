package com.bandg.uploadfiles.jwt;

import com.bandg.uploadfiles.configuration.JwtConfig;

import javax.crypto.SecretKey;
import javax.servlet.*;
import java.io.IOException;

public class JwtTokenVerifier implements Filter {

    public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {

    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

    }
}
