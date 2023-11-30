package com.example.authservice.common.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendMessageException extends RuntimeException {

  public SendMessageException(String message) {
    super(message);
  }
}
