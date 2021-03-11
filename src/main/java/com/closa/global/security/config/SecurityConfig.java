package com.closa.global.security.config;

import com.closa.global.security.service.UserService;
import com.closa.global.throwables.exceptions.AccessDeniedExceptionHandler;
import com.closa.global.throwables.exceptions.NotAuthorisedtoRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    UserService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth  ) throws Exception{
        auth.userDetailsService(userDetailsService);
    }
/*
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new NotAuthorisedtoRESTException();
    }*/

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/signout").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/roles").permitAll()
                .antMatchers("/swagger-ui**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/user").hasRole("SYSADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    AccessDeniedHandler defaultAccessDeniedHandler = new AccessDeniedExceptionHandler();
                    defaultAccessDeniedHandler.handle(request, response, accessDeniedException);
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().httpStrictTransportSecurity().disable();
    }

  /*  @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST"));
        config.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-Width", "X-PINGOTHER", "Content-Type", "Authorization"));
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/auth/**", config);
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }*/

}
