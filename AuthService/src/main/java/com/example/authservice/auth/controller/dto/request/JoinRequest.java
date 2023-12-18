package com.example.authservice.auth.controller.dto.request;

import com.example.authservice.auth.constant.Role;
import com.example.authservice.auth.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

  @Email(message = "올바른 이메일 형식이 아닙니다.")
  @NotBlank(message = "필수 정보입니다.")
  private String emailId;

  @NotBlank(message = "필수 정보입니다.")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=-])(?=\\S+$).{8,20}$",
      message = "공백 없이 8자리 ~ 20자리 이내 최소 하나 이상의 숫자, 영문자, 특수 문자를 포함해야 합니다.")
  private String password;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @NotBlank(message = "필수 정보입니다.")
  private Date birthOfDate;

  public Member toEntity(String encodedPassword, Role role) {
    return Member.builder()
        .emailId(emailId)
        .password(encodedPassword)
        .birthOfDate(birthOfDate)
        .build();
  }

}
