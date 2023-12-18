package com.example.mypageservice.Dto;

import com.example.mypageservice.Entity.MemberProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileRequest {
    private String nickname;

    private String introduction;

    public MemberProfile toEntity() {
        return MemberProfile.builder()
                .nickname(nickname)
                .introduction(introduction)
                .build();
    }
}
