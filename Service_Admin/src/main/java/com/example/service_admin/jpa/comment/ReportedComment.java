package com.example.service_admin.jpa.comment;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class ReportedComment {
    @Id
    private long id;
    private boolean abuse;
    private boolean improper_speaking;
    private String other_reason;
    private LocalDateTime datetime;

    public ReportedComment() {

    }
}
