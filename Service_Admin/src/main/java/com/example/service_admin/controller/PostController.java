package com.example.service_admin.controller;

import com.example.service_admin.dto.reported_post.response.ReportedPostResponse;
import com.example.service_admin.jpa.post.ReportedPost;
import com.example.service_admin.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/repoposts")
    public List<ReportedPostResponse> getReportedPosts(){
        return this.postService.getReportedPost();
    }

    @DeleteMapping("/passpost")
    public void passPost(@RequestParam long id){
        this.postService.removeAtTable(id);
    }

    @DeleteMapping("/delpost")
    public void delPost(@RequestParam long id){
        this.postService.removeAtTable(id);
        // 홍정우씨에게 post삭제 요청하기
    }

}
