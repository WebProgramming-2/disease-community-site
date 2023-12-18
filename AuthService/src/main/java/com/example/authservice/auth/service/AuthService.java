package com.example.authservice.auth.service;

import com.example.authservice.auth.constant.CertificationResult;
import com.example.authservice.auth.constant.Role;
import com.example.authservice.auth.controller.dto.request.FindEmailRequest;
import com.example.authservice.auth.controller.dto.request.JoinRequest;
import com.example.authservice.auth.controller.dto.request.LoginRequest;
import com.example.authservice.auth.controller.dto.request.MailConfirmRequest;
import com.example.authservice.auth.controller.dto.response.EmailConfirmResponse;
import com.example.authservice.auth.controller.dto.response.EmailResponse;
import com.example.authservice.auth.domain.Member;
import com.example.authservice.auth.repository.MemberRepository;
import com.example.authservice.common.exception.custom.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

  private final MemberRepository memberRepository;

  private final MailService mailService;

  private final PasswordEncoder passwordEncoder;

  public void join(JoinRequest joinRequest) {
    String encodedPassword = passwordEncoder.encode(joinRequest.getPassword());
    Member member = joinRequest.toEntity(encodedPassword, Role.USER);
    checkDuplicatedEmail(member.getEmailId());
    memberRepository.save(member);
  }

  public void sendCodeToEmail(String toEmail) {
//    checkDuplicatedEmail(toEmail);
    mailService.sendCodeToEmail(toEmail);
  }

  public EmailConfirmResponse checkEmailCode(MailConfirmRequest mailConfirmRequest) {
    boolean success = mailService.isEqual(mailConfirmRequest.getEmail(), mailConfirmRequest.getCode());
    if(!success) {
      return EmailConfirmResponse.from(CertificationResult.FAIL);
    }
    return EmailConfirmResponse.from(CertificationResult.SUCCESS);
  }

  private void checkDuplicatedEmail(String toEmail) {
    memberRepository.findByEmailId(toEmail).ifPresent(member -> {throw new DuplicateEmailException("중복된 이메일 입니다.");});
  }

  public void login(LoginRequest loginRequest) {

  }

  public EmailResponse findEmail(FindEmailRequest findEmailRequest) {
    System.out.println(findEmailRequest.getPhoneNumber());
    Member member = memberRepository.findByPhoneNumber(
        findEmailRequest.getPhoneNumber()).orElseThrow(()-> new IllegalArgumentException(
        "해당 멤버는 존재하지 않습니다."));

    return EmailResponse.From(member.getEmailId());
  }

  public EmailResponse emailResponse(String email) {
    return EmailResponse.From(email);
  }

  public EmailConfirmResponse findPassword(MailConfirmRequest mailConfirmRequest) {
    boolean success = mailService.isEqual(mailConfirmRequest.getEmail(), mailConfirmRequest.getCode());
    if(!success) {
      return EmailConfirmResponse.from(CertificationResult.FAIL);
    }
    Member member = memberRepository.findByEmailId(mailConfirmRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException(
        "해당 멤버는 존재하지 않습니다."));
    return EmailConfirmResponse.from(CertificationResult.SUCCESS, member.getPassword());
  }
}
