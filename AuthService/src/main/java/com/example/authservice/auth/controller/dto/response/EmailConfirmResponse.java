package com.example.authservice.auth.controller.dto.response;

import com.example.authservice.auth.constant.CertificationResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailConfirmResponse {

  private String certificationResult;

  private String message;

  @JsonInclude(Include.NON_NULL)
  private String password;

  @Builder
  public EmailConfirmResponse(String certificationResult, String message, String password) {
    this.certificationResult = certificationResult;
    this.message = message;
    this.password = password;
  }

  public static EmailConfirmResponse from(CertificationResult certificationResult){
    return EmailConfirmResponse.builder()
        .certificationResult(String.valueOf(certificationResult))
        .message(certificationResult.getMessage())
        .build();
  }

  public static EmailConfirmResponse from(CertificationResult certificationResult, String password){
    return EmailConfirmResponse.builder()
        .certificationResult(String.valueOf(certificationResult))
        .message(certificationResult.getMessage())
        .password(password)
        .build();
  }
}
