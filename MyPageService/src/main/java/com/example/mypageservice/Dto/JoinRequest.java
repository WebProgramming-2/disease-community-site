package com.example.mypageservice.Dto;

import com.example.mypageservice.Entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    private String emailId;

    private String password;

    private String birthOfDate;

    private String nickname;

    private String myInfo;

    public Member toEntity() {
        return Member.builder()
                .emailId(emailId)
                .password(password)
                .birthOfDate(birthOfDate)
                .nickname(nickname)
                .myInfo(myInfo)
                .build();
    }

}
