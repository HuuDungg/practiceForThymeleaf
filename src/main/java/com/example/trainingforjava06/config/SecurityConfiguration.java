package com.example.trainingforjava06.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Value("${huudung.secret.key}")
    private String secretKey;

    //khai báo thuật toán để hashcode secret key
    public final MacAlgorithm macAlgorithm = MacAlgorithm.HS512;
    //encoder password with bcrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authz ->
                        authz
                                .requestMatchers("/user/login/**").permitAll()
                                .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(f -> f.disable())
        .csrf(c -> c.disable());

        return http.build();
    }

    //giải mã secret key trong biến mối trường ra rồi lại hask code. phiền vl
    private SecretKey getSecretKey(){
        byte[] Keybytes = Base64.from(secretKey).decode();
        return new SecretKeySpec(Keybytes, 0, Keybytes.length, macAlgorithm.getName());
    }

    //mã hoá xong thì phải báo cho springboot biết key đã mã hoá để springboot đem đi mã hoá token == setup emcoder
    @Bean
    public JwtEncoder jwtEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(getSecretKey())
                .macAlgorithm(macAlgorithm)
                .build();

        return jwtDecoder;

    }

}
