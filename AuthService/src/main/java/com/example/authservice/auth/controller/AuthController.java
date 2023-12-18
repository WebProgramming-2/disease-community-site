package com.example.authservice.auth.controller;

import com.example.authservice.auth.constant.CertificationResult;
import com.example.authservice.auth.controller.dto.request.FindEmailRequest;
import com.example.authservice.auth.controller.dto.request.MailConfirmRequest;
import com.example.authservice.auth.controller.dto.response.EmailConfirmResponse;
import com.example.authservice.auth.controller.dto.response.EmailResponse;
import com.example.authservice.auth.service.AuthService;
import com.example.authservice.common.constant.ResponseStatus;
import com.example.authservice.common.message.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth-service")
public class AuthController {

  private final AuthService authService;

  @GetMapping("/home")
  public String home(){
    return "home";
  }

  @PostMapping("/login")
  public String getToken(HttpServletResponse response) {
    return response.getHeader("accessHeader");
  }

  @GetMapping("/emails/code-request")
  public ResponseEntity<ResponseMessage> checkEmailRequest(@RequestParam("email") @Email  String email) {
    authService.sendCodeToEmail(email);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping("/emails/check")
  public ResponseEntity<ResponseMessage> checkEmailCode(@ModelAttribute MailConfirmRequest mailConfirmRequest) {
    EmailConfirmResponse emailConfirmResponse = authService.checkEmailCode(mailConfirmRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, emailConfirmResponse),
        HttpStatus.OK);
  }

  @PostMapping("/find-email")
  public ResponseEntity<ResponseMessage> findEmail(@RequestBody FindEmailRequest findEmailRequest) {
    EmailResponse emailResponse = authService.findEmail(findEmailRequest);
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, emailResponse), HttpStatus.OK);
  }

  @PostMapping("/find-password")
  public ResponseEntity<ResponseMessage> findPassword(@RequestBody MailConfirmRequest mailConfirmRequest){
    EmailConfirmResponse emailConfirmResponse = authService.findPassword(mailConfirmRequest);
    if (emailConfirmResponse.getCertificationResult().equals(String.valueOf(CertificationResult.SUCCESS))) {
      return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.OK, emailConfirmResponse), HttpStatus.OK);
    }
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.CERTIFICATION_FAIL, emailConfirmResponse), HttpStatus.BAD_REQUEST);
  }
}
