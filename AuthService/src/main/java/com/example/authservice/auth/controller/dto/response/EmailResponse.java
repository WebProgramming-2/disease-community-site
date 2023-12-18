package com.example.authservice.auth.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailResponse {

  private String email;

  @Builder
  public EmailResponse(String email) {
    this.email = email;
  }

  public static EmailResponse From(String email) {
    return EmailResponse.builder()
        .email(email)
        .build();
  }
}
