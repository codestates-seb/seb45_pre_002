package com.codestates.stackoverflow.security.config;

import com.codestates.stackoverflow.member.repository.MemberRepository;
import com.codestates.stackoverflow.security.filter.JwtAuthenticationFilter;
import com.codestates.stackoverflow.security.filter.JwtVerificationFilter;
import com.codestates.stackoverflow.security.handler.MemberAccessDeniedHandler;
import com.codestates.stackoverflow.security.handler.MemberAuthenticationEntryPoint;
import com.codestates.stackoverflow.security.handler.MemberAuthenticationFailureHandler;
import com.codestates.stackoverflow.security.handler.MemberAuthenticationSuccessHandler;
import com.codestates.stackoverflow.security.provider.JwtTokenProvider;
import com.codestates.stackoverflow.security.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberAuthenticationSuccessHandler memberAuthenticationSuccessHandler;

    private final MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;

    private final CustomAuthorityUtils authorityUtils;

    private final MemberAuthenticationEntryPoint authenticationEntryPoint;

    private final MemberAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .headers().frameOptions().disable() //h2사용을 위해 추가
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .apply(new CustomFilterConfig())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .antMatchers(HttpMethod.GET, "/**").permitAll() // 테스트용
                        .antMatchers(HttpMethod.PATCH, "/**").permitAll() // 테스트용
                        .antMatchers(HttpMethod.POST, "/members/jwtTest").hasRole("USER") //테스트용

                        .antMatchers(HttpMethod.POST, "/members/login", "/members/signup").permitAll()
                        .anyRequest().permitAll());

        return httpSecurity.build();
    }

    private class CustomFilterConfig extends AbstractHttpConfigurer<CustomFilterConfig, HttpSecurity> {

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(memberAuthenticationSuccessHandler);
            jwtAuthenticationFilter.setAuthenticationFailureHandler(memberAuthenticationFailureHandler);

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenProvider, authorityUtils);

            httpSecurity
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}