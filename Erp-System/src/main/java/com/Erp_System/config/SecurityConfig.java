package com.Erp_System.config;

import com.Erp_System.Security.AuthEntryPoint;
import com.Erp_System.Security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;
    private final String[] PUBLIC_URLS = {
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/test"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(Arrays.asList("https://domain2.com","http://localhost:4200"));
                configuration.addAllowedOriginPattern("*");
                configuration.addAllowedHeader("Authorization");
                configuration.addAllowedHeader("Content-Type");
                configuration.addAllowedHeader("Accept");
                configuration.addAllowedMethod("GET");
                configuration.addAllowedMethod("POST");
                configuration.addAllowedMethod("DELETE");
                configuration.addAllowedMethod("PUT");
                configuration.addAllowedMethod("OPTIONS");
                configuration.setMaxAge(3600L);
                url .registerCorsConfiguration("/**", configuration);

                return configuration;
            }
        }));
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                                .requestMatchers(HttpMethod.POST,"/user").permitAll()
                                .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/user/**").permitAll()
                                .requestMatchers(PUBLIC_URLS).permitAll()
                                .anyRequest().permitAll()
                        );



            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            http.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint));

                    return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
