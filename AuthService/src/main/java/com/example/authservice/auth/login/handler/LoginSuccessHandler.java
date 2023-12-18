package com.example.authservice.auth.login.handler;

import com.example.authservice.auth.jwt.service.JwtService;
import com.example.authservice.auth.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final JwtService jwtService;
  private final MemberRepository memberRepository;

  @Value("${jwt.access.expiration}")
  private String accessTokenExpiration;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    String email = extractUsername(authentication); // 인증 정보에서 Username(email) 추출
    String accessToken = jwtService.createAccessToken(email); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
    String refreshToken = jwtService.createRefreshToken(); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급

    jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken); // 응답 헤더에 AccessToken, RefreshToken 실어서 응답

    memberRepository.findByEmailId(email)
        .ifPresent(member -> {
          member.updateRefreshToken(refreshToken);
          memberRepository.saveAndFlush(member);
        });
  }

  private String extractUsername(Authentication authentication) {
    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    return userDetails.getUsername();
  }
}