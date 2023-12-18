package com.example.authservice.auth.login;

import com.example.authservice.auth.domain.Member;
import java.util.Map;
import lombok.Getter;

@Getter
public class UserAdapter extends CustomUserDetails {

  private final Member member;
  private Map<String, Object> attributes;

  public UserAdapter(final Member member) {
    super(member);
    this.member = member;
  }
}