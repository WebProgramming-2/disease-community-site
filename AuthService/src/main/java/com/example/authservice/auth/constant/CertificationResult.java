package com.example.authservice.auth.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CertificationResult {
  SUCCESS("인증에 성공하였습니다."),
  FAIL("인증에 실패하였습니다. 다시 입력해주세요");

  private final String message;
}
