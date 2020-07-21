package com.bandg.uploadfiles.configuration;

import com.bandg.uploadfiles.jwt.JwtTokenVerifier;
import com.bandg.uploadfiles.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.bandg.uploadfiles.jwt.PersonAuthenticationManager;
import com.bandg.uploadfiles.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final PersonService personService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private  final PersonAuthenticationManager authenticationManager;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     PersonService personService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig,
                                     PersonAuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.authenticationManager = authenticationManager;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS ,"/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/register")
                .permitAll()
                .and()

                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager
                        , jwtConfig
                        , secretKey, passwordEncoder))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig, personService),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(personService);
        return provider;
    }
}