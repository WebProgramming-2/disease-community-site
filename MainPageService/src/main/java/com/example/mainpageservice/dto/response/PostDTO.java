package com.example.mainpageservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String title;
    private LocalDateTime create_at;
    private long views;
    private long likeCount;
    private String nickname;
    private String category;
    private long commentCount;
}
