package com.example.service_admin.controller;

import com.example.service_admin.dto.reported_comment.response.ReportedCommentResponse;
import com.example.service_admin.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/repocomments")
    public List<ReportedCommentResponse> getReportedComments(){
        return this.commentService.getReportedComments();
    }

    @DeleteMapping("/passcomments")
    public void passComment(@RequestParam long id){
        this.commentService.removeAtTable(id);
    }

    @DeleteMapping("/delcomments")
    public void delComment(@RequestParam long id){
        this.commentService.removeAtTable(id);
        // 홍정우씨에게 post삭제 요청하기
    }

}
