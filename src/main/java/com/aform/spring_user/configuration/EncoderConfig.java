package com.aform.spring_user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {// 순환참조 문제가 발생할 수 있기 때문에 filterchain과 다른 클래스에 선언
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // JwtDecoder jwtDecoder() {
    //     return NimbusJwtDecoder.withPublicKey(this.key).build();
    // }

    // @Bean
    // JwtEncoder jwtEncoder() {
    //     JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
    //     JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    //     return new NimbusJwtEncoder(jwks);
    // }
}
