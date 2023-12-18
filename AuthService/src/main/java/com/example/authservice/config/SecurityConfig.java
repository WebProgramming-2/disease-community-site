package com.example.authservice.config;

import com.example.authservice.auth.jwt.filter.JwtAuthenticationProcessingFilter;
import com.example.authservice.auth.jwt.service.JwtService;
import com.example.authservice.auth.login.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import com.example.authservice.auth.login.handler.LoginFailureHandler;
import com.example.authservice.auth.login.handler.LoginSuccessHandler;
import com.example.authservice.auth.login.service.LoginService;
import com.example.authservice.auth.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginService loginService;
  private final JwtService jwtService;
  private final MemberRepository memberRepository;
  private final ObjectMapper objectMapper;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(new AntPathRequestMatcher("/resource/**"))
        .requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
        .requestMatchers(new AntPathRequestMatcher("/css/**"))
        .requestMatchers(new AntPathRequestMatcher("/js/**"))
        .requestMatchers(new AntPathRequestMatcher("/img/**"))
        .requestMatchers(new AntPathRequestMatcher("/lib/**"));
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws
      Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .headers(HeadersConfigurer::disable) //H2 콘솔(iframe)을 사용하기 때문에 비활성화

        .sessionManagement(sessionManagement ->
            sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .exceptionHandling(e -> e
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

        /**
         * 아래의 경로는 사용자의 권한과 상관없이 접근 가능
         */

        .authorizeHttpRequests(authorizeHttpRequests ->
            authorizeHttpRequests.anyRequest().permitAll());
//                .requestMatchers(new MvcRequestMatcher(introspector, "/")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/join")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/login")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/emails/code-request")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/emails/check")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/find-email")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/auth-service/find-password")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/mainPage-service/**")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/post-service/posts")).permitAll()
//                .requestMatchers(new MvcRequestMatcher(introspector, "/admin/**")).hasRole("ADMIN")
//                .requestMatchers(new MvcRequestMatcher(introspector, "/users/**")).permitAll()
//                .anyRequest().authenticated());



    http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
    http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * AuthenticationManager 설정 후 등록
   * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
   * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
   * UserDetailsService는 커스텀 LoginService로 등록
   * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
   */
  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(loginService);
    return new ProviderManager(provider);
  }

  /**
   * 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
   */
  @Bean
  public LoginSuccessHandler loginSuccessHandler() {
    return new LoginSuccessHandler(jwtService, memberRepository);
  }

  /**
   * 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
   */
  @Bean
  public LoginFailureHandler loginFailureHandler() {
    return new LoginFailureHandler();
  }

  /**
   * CustomJsonUsernamePasswordAuthenticationFilter 빈 등록
   * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
   * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
   * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
   */
  @Bean
  public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
    CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
        = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
    customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
    customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
    customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
    return customJsonUsernamePasswordLoginFilter;
  }

  @Bean
  public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
    return new JwtAuthenticationProcessingFilter(jwtService,
        memberRepository);
  }
}
