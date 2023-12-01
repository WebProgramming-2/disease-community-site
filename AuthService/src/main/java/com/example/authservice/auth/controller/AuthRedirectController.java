package com.example.authservice.auth.controller;

import com.example.authservice.auth.controller.dto.request.JoinRequest;
import com.example.authservice.auth.controller.dto.request.LoginRequest;
import com.example.authservice.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth-service")
public class AuthRedirectController {

  private final AuthService authService;

  @PostMapping("/join-form")
  public String join(@ModelAttribute JoinRequest joinRequest){
    authService.join(joinRequest);
    return "redirect:/login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute LoginRequest LoginRequest) {

  }

}
