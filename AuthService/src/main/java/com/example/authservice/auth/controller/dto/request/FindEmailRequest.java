package com.example.authservice.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FindEmailRequest {

  private Long phoneNumber;

}
