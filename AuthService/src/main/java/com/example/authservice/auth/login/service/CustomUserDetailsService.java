package com.example.authservice.auth.login.service;

import com.example.authservice.auth.domain.Member;
import com.example.authservice.auth.login.UserAdapter;
import com.example.authservice.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    Member user = memberRepository.findByEmailId(email).orElseThrow(() ->
        new UsernameNotFoundException("사용자가 존재하지 않습니다."));
    return new UserAdapter(user);
  }
}