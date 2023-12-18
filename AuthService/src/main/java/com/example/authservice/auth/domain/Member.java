package com.example.authservice.auth.domain;

import com.example.authservice.auth.constant.Role;
import com.example.authservice.common.entity.BaseEntity;
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
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String emailId;

  private String password;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthOfDate;

  private Long phoneNumber;

  private String nickname;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String profileImage;

  private String refreshToken;

  @Builder
  public Member(String emailId, String password, Date birthOfDate, Role role, Long phoneNumber) {
    this.emailId = emailId;
    this.password = password;
    this.birthOfDate = birthOfDate;
    this.role = role;
    this.phoneNumber = phoneNumber;
  }

  public void authorizeUser() {
    this.role = Role.USER;
  }

  public void updateRefreshToken(String updateRefreshToken) {
    this.refreshToken = updateRefreshToken;
  }
}



