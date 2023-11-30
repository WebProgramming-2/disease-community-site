package com.example.authservice.auth.vo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class EmailBoards {

  private final Map<String, String> emailBoards = new ConcurrentHashMap<>();

  public void putEmailBoard(String email, String code) {
    emailBoards.put(email, code);
  }

  public void removeEmailBoard(String email) {
    emailBoards.remove(email);
  }

  public String getValue(String email) {
    return emailBoards.get(email);
  }

  public Map<String, String> getEmailsBoard() {
    return new ConcurrentHashMap<>(emailBoards);
  }

}
