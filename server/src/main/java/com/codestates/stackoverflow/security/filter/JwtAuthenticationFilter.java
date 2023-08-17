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
import org.springframework.security.authentication.AuthenticationManager;
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

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final MemberRepository memberRepository;

    @SneakyThrows // 예외 발생 시 RuntimeException 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginDto.PostDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.PostDto.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Member member = (Member) authResult.getPrincipal();

        Member findMember = memberRepository.findByEmail(member.getUsername()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        String accessToken = delegateAccessToken(findMember);

        response.setHeader("Authorization", accessToken);

        //로그인한 멤버의 memberId, email, username 을 JSON 형식으로 응답하는 기능 추가
        LoginDto.ResponseDto loginDto = new LoginDto.ResponseDto();
        loginDto.setMember_id(findMember.getMemberId());
        loginDto.setEmail(findMember.getEmail());
        loginDto.setUsername(findMember.getUsername());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(loginDto);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("memberId", member.getMemberId());
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());

        String accessToken = jwtTokenProvider.createAccessToken(claims, member.getUsername());

        return accessToken;
    }
}
