package com.example.authservice.auth.controller.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MailConfirmRequest {
  @Email
  private String email;
  private String code;
}
