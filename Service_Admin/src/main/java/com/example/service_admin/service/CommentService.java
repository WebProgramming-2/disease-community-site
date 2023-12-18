package com.example.service_admin.service;

import com.example.service_admin.dto.reported_comment.response.ReportedCommentResponse;
import com.example.service_admin.jpa.comment.ReportedComment;
import com.example.service_admin.jpa.comment.ReportedCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ReportedCommentRepository ReportedCommentRepository;

    public List<ReportedCommentResponse> getReportedComments(){
        return this.ReportedCommentRepository.findAll().stream()
                .map(ReportedCommentResponse::new)
                .collect(Collectors.toList());
    }

    public void removeAtTable(long id) {
        Optional<ReportedComment> optional= this.ReportedCommentRepository.findById(id);
        if (optional.isPresent()){
            ReportedComment comment = optional.get();
            this.ReportedCommentRepository.delete(comment);
        }else
            throw new IllegalArgumentException("");
    }
}
