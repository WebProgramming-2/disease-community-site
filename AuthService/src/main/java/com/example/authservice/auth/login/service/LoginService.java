package com.example.authservice.auth.login.service;

import com.example.authservice.auth.domain.Member;
import com.example.authservice.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmailId(email)
        .orElseThrow(() -> new UsernameNotFoundException("[Error] 해당 이메일이 존재하지 않습니다."));

    return org.springframework.security.core.userdetails.User.builder()
        .username(member.getEmailId())
        .password(member.getPassword())
        .roles(String.valueOf(member.getRole()))
        .build();
  }
}
