package com.example.service_admin.jpa.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login_id;

    private String password;

    private LocalDate date_of_birth;

    private String nickname;

    private boolean badge_status;

    private boolean admin;

    private String profile_image;

    private int warn_count;

    protected User() {}

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /** set warn_count to count. **/
    public void setWarn_count(int count){warn_count = count;}

}

