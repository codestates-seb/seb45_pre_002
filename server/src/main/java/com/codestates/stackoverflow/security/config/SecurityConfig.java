package com.codestates.stackoverflow.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable() //h2사용을 위해 추가
                .and()
                .authorizeRequests()
                .antMatchers("/", "/members/signup", "/h2/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2/**")    //h2만 csrf 면제처리
//                .and()
                .formLogin()
                .loginPage("/members/login") // 로그인 페이지 URL
                .loginProcessingUrl("/process_login")
                .failureUrl("/members/login?error")
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트할 URL
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/members/logout") // 로그아웃 URL
                .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트할 URL
                .permitAll();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}