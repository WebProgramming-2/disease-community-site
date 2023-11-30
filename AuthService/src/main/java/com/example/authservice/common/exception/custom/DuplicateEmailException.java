package com.example.authservice.common.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateEmailException extends RuntimeException{

  public DuplicateEmailException(String message) {
    super(message);
  }
}
