package com.example.service_admin.dto.user.response;

import com.example.service_admin.jpa.user.ReportedUser;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportedUserResponse {

    private final long id;
    private final LocalDateTime reported_date;
    private final boolean abuse;
    private final boolean improper_profile;
    private final String other_reason;
    private final String post_link;

    public ReportedUserResponse(ReportedUser reportedUser){
        this.id = reportedUser.getId();
        this.post_link = reportedUser.getPost_link();
        this.abuse = reportedUser.isAbuse();
        this.improper_profile = reportedUser.isImproper_profile();
        this.other_reason = reportedUser.getOther_reason();
        this.reported_date = reportedUser.getReported_date();
    }
}
