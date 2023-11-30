package com.example.authservice.auth.controller.dto.response;

import com.example.authservice.auth.constant.CertificationResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailConfirmResponse {

  private String certificationResult;

  private String message;

  @Builder
  public EmailConfirmResponse(String certificationResult, String message) {
    this.certificationResult = certificationResult;
    this.message = message;
  }

  public static EmailConfirmResponse from(CertificationResult certificationResult){
    return EmailConfirmResponse.builder()
        .certificationResult(String.valueOf(certificationResult))
        .message(certificationResult.getMessage())
        .build();
  }
}
