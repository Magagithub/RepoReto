package com.everis.gatewaySecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity()
public class WebfluxSecurityConfig {
    @Autowired
    private UserRepository userRepository;

    // Para encriptar las contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public ReactiveUserDetailsService userDetailsService() {

        return (username) -> userRepository.findByUsername(username);
    }

    @Bean
    public SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http) {
        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec =http.authorizeExchange();
        authorizeExchangeSpec.pathMatchers("/user/**").hasRole("ADMIN");
                authorizeExchangeSpec.pathMatchers(
                        "/FACTURA-ASINCRONO-MONGO/**",
                        "/CLIENTE-SINCRONO-MYSQL/**",
                        "/PAGO-ASINCRONO-MONGO/**",
                        "/VISITA-SINCRONO-MYSQL/**").hasRole("ADMIN");
                authorizeExchangeSpec.and().csrf().disable().httpBasic();
                //authorizeExchangeSpec.pathMatchers("/**").authenticated();

               // authorizeExchangeSpec.pathMatchers("/user/**").permitAll();

    return http.build();

    }
}
