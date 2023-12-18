package com.example.authservice.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
  OK("요청에 성공하였습니다."),
  BAD_REQUEST("잘못된 요청입니다."),
  SEND_MAIL_FAIL("인증 번호 전송에 실패하였습니다."),
  CERTIFICATION_FAIL("인증에 실패하였습니다."),
  BAD_CREDENTIAL("아이디 혹은 비밀번호가 일치하지 않습니다."),
  UNAUTHORIZED("로그인 후 접근 가능합니다."),
  FORBIDDEN("접근 권한이 없습니다."),
  INTERNAL_SEVER_ERROR("서버 에러"),
  DUPLICATED_EMAIL("이미 사용 중인 이메일입니다.");

  private final String resultMessage;
}
