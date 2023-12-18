package com.example.authservice.auth.domain;

import com.example.authservice.auth.constant.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String emailId;

  private String password;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthOfDate;

  private String nickname;

  private boolean badgeStatus;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String profileImage;

  @Builder
  public Member(String emailId, String password, Date birthOfDate, Role role) {
    this.emailId = emailId;
    this.password = password;
    this.birthOfDate = birthOfDate;
    this.role = role;
  }
}



