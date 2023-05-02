package com.aform.spring_user.configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;


// @EnableWebSecurity(debug = true) // 기본적인 웹 보안 활성화
// 추가적인 설정을 위해 WebSecurityConfigurer를 implements하거나
// WebSecurityConfigurerAdapter를 extends하는 방법이 있다.
@Configuration
public class SecurityConfig {
    // @Value("${jwt.public.key}")
    // RSAPublicKey key;

    // @Value("${jwt.private.key}")
    // RSAPrivateKey priv;

    /*
     * 보호할 URL경로 정의 / , /app은 인증이 필요하지 않도록 구성
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .csrf().disable() // front 가 분리된 환경을 가정하고 있기 때문에 csrf를 disable
                .httpBasic().disable()//token 기반 인증이기 때문에 기본적인 http 인증은 disable
                .cors().and()
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션저장기능                                                                                              // 제거
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/app/user/login", "/app/user/join").permitAll()
                        .anyRequest().authenticated()
                );
                // .exceptionHandling((exceptions) -> exceptions //
                //         .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                //         .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
        // TODO csrf 토큰 발급
        // .formLogin((form) -> form
        // .loginPage("/app/user/login")
        // .permitAll()));
        // .logout((logout) -> logout.permitAll());
        //jjwt 라이브러리가 Java에서 JWT를 생성하고 검증하는 데 사용되는 반면 OAuth 2.0은 토큰 전송 방식을 지정하는 프로토콜


        return http.build();

    }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    //         throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    // return null; // 여기다가 db설정?

    // // configure Web security...

    // }

    // @Bean
    // UserDetailsService users() { // db에서 Authentication의 details들을 리턴. 이 정보를 가지고 PasswordEncoder를 사용해서 input된
    //                              // 비밀번호와 매칭
    //     // @formatter:off
	// 	return new InMemoryUserDetailsManager(
	// 		User.withUsername("user")
	// 			.password("{noop}password")
	// 			.authorities("app")
	// 			.build()
	// 	);
	// 	// @formatter:on
    // }


}
