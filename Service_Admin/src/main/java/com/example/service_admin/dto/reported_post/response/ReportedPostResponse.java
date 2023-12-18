package com.example.service_admin.dto.reported_post.response;

import com.example.service_admin.jpa.post.ReportedPost;

import java.time.LocalDateTime;

public class ReportedPostResponse {

    private long id;
    private boolean abuse;
    private boolean improper_speaking;
    private String other_reason;
    private LocalDateTime reportDate;

    public ReportedPostResponse(ReportedPost reportedPost){
        this.id = reportedPost.getId();
        this.abuse = reportedPost.isAbuse();
        this.improper_speaking = reportedPost.isImproper_speaking();
        this.other_reason = reportedPost.getOther_reason();
        this.reportDate = reportedPost.getReported_date();
    }
}
