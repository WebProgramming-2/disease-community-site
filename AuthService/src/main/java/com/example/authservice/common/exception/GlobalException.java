package com.example.authservice.common.exception;

import com.example.authservice.common.constant.ResponseStatus;
import com.example.authservice.common.exception.custom.LogicException;
import com.example.authservice.common.exception.custom.SendMessageException;
import com.example.authservice.common.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalException {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseMessage> IllegalArgumentException() {
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.BAD_REQUEST),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LogicException.class)
  public ResponseEntity<ResponseMessage> LogicException() {
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.INTERNAL_SEVER_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(SendMessageException.class)
  public ResponseEntity<ResponseMessage> SendMessageException() {
    return new ResponseEntity<>(ResponseMessage.of(ResponseStatus.SEND_MAIL_FAIL),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public static ResponseEntity<ResponseMessage> validationException(
      MethodArgumentNotValidException e) {

    BindingResult bindingResult = e.getBindingResult();
    StringBuilder errorMessage = new StringBuilder();

    bindingResult.getFieldErrors().forEach(
        column -> {
          errorMessage.append(column.getField());
          errorMessage.append(": ");
          errorMessage.append(column.getDefaultMessage());
          errorMessage.append("\n");
        }
    );
    return new ResponseEntity<>(
        ResponseMessage.of(ResponseStatus.BAD_REQUEST, errorMessage.toString()),
        HttpStatus.BAD_REQUEST);
  }
}
