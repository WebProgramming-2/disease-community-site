package com.example.authservice.auth.controller;

import com.example.authservice.auth.controller.dto.request.JoinRequest;
import com.example.authservice.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth-service")
public class AuthRedirectController {

  private final AuthService authService;

  @PostMapping("/join")
  public String join(@ModelAttribute JoinRequest joinRequest){
    authService.join(joinRequest);
    return "redirect:http://localhost:8000/auth-service/login";
  }

  @GetMapping("/join")
  public String join(){
    return "/join";
  }


  @GetMapping("/login")
  public String login() {
//    authService.login(loginRequest);
    return "/login";
  }

  @GetMapping("/find-email")
  public String findEmailPage() {
    return "/find-email";
  }

  @GetMapping("/find-password")
  public String findPasswordPage() {
    return "/find-password";
  }

}
