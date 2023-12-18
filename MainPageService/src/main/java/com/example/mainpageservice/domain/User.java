package com.example.mainpageservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class User {
    @Id
    private long id;
    private String login_id;
    private String password;
    private LocalDate date_of_birth;
    private String nickname;
    private boolean badge_status;
    private boolean admin;
    private String profile_image;

    public User() {

    }
}
