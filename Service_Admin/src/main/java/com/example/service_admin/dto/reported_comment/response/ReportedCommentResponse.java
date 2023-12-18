package com.example.service_admin.dto.reported_comment.response;

import com.example.service_admin.jpa.comment.ReportedComment;
import com.example.service_admin.jpa.post.ReportedPost;

import java.time.LocalDateTime;

public class ReportedCommentResponse {

    private long id;
    private boolean abuse;
    private boolean improper_speaking;
    private String other_reason;
    private LocalDateTime reportDate;

    public ReportedCommentResponse(ReportedComment reportedComment){
        this.id = reportedComment.getId();
        this.abuse = reportedComment.isAbuse();
        this.improper_speaking = reportedComment.isImproper_speaking();
        this.other_reason = reportedComment.getOther_reason();
        this.reportDate = reportedComment.getDatetime();
    }
}
