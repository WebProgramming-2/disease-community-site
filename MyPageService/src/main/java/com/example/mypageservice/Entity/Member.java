package com.example.mypageservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String emailId;

    private String password;

    private String birthOfDate;

    private String nickname;

    private String myInfo;

    private String profileImage;

    public void update(String nickname, String imageURL){
        this.nickname = nickname;
        this.profileImage = imageURL;
    }
    public void create(String emailId, String password, String birthOfDate, String nickname, String myInfo){
        this.emailId = emailId;
        this.password = password;
        this.birthOfDate = birthOfDate;
        this.nickname = nickname;
        this.myInfo = myInfo;
    }

    @Builder
    public Member(String emailId, String password, String birthOfDate) {
        this.emailId = emailId;
        this.password = password;
        this.birthOfDate = birthOfDate;
    }

//    @Builder
//    public Member(String emailId, String password, String birthOfDate, String nickname, String myInfo){
//        this.emailId = emailId;
//        this.password = password;
//        this.birthOfDate = birthOfDate;
//        this.nickname = nickname;
//        this.myInfo = myInfo;
//    }
}
