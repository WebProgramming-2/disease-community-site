package com.example.authservice.auth.ecommerce;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ainPage-service")
public interface MainClient {
  @GetMapping("/")
  String getMainPage();
}
