package com.example.service_admin.service;

import com.example.service_admin.dto.category.response.InquiredCategoryResponse;
import com.example.service_admin.dto.reported_post.response.ReportedPostResponse;
import com.example.service_admin.jpa.post.ReportedPost;
import com.example.service_admin.jpa.post.ReportedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ReportedPostRepository reportedPostRepository;

    public List<ReportedPostResponse> getReportedPost(){
        return this.reportedPostRepository.findAll().stream()
                .map(ReportedPostResponse::new)
                .collect(Collectors.toList());
    }

    public void removeAtTable(long id) {
        Optional<ReportedPost> optional= this.reportedPostRepository.findById(id);
        if (optional.isPresent()){
            ReportedPost post = optional.get();
            this.reportedPostRepository.delete(post);
        }else
            throw new IllegalArgumentException("");
    }
}
