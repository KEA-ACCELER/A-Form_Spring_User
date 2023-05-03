package com.aform.spring_user.configuration;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aform.spring_user.service.UserService;

import lombok.RequiredArgsConstructor;


@EnableWebSecurity(debug = true)
// @EnableWebSecurity(debug = true) // 기본적인 웹 보안 활성화
// 추가적인 설정을 위해 WebSecurityConfigurer를 implements하거나
// WebSecurityConfigurerAdapter를 extends하는 방법이 있다.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

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
                )
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class);
                // .exceptionHandling((exceptions) -> exceptions //
                //         .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                //         .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

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


}
