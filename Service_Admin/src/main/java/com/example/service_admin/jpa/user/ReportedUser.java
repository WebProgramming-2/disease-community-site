package com.example.service_admin.jpa.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class ReportedUser {

    @Id
    private long id;
    private LocalDateTime reported_date;
    private boolean abuse;
    private boolean improper_profile;
    private String other_reason;
    private String post_link;

    public ReportedUser(){}
}
