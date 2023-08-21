package com.codestates.stackoverflow.security.filter;

import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.repository.MemberRepository;
import com.codestates.stackoverflow.security.dto.LoginDto;
import com.codestates.stackoverflow.security.provider.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final MemberRepository memberRepository;

    @SneakyThrows // 예외 발생 시 RuntimeException 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginDto.PostDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.PostDto.class);

            //이메일 인증이 완료되지 않은 멤버가 로그인 시 거절
            Member member = memberRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

            if (!member.isUserStatus()) {
                throw new DisabledException("need email authentication");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
