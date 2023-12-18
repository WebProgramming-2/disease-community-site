package com.example.mainpageservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponseDTO {
    private List<PostDTO> posts;
    private int currentPageNumber;
    private int totalPage;
    private int userDisplay;
    private long totalElements;
}
