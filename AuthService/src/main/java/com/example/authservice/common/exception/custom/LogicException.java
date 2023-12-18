package com.example.authservice.common.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogicException extends RuntimeException{

  public LogicException(String message) {
    super(message);
  }
}
