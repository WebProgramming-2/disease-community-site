package com.example.service_admin.jpa.post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;
@Entity
@Getter
public class ReportedPost {
    @Id
    private long id;
    private boolean abuse;
    private boolean improper_speaking;
    private String other_reason;
    private LocalDateTime reported_date;

    public ReportedPost() {

    }
}
