package com.example.mainpageservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    private long id;
    private String title;
    private String content;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private long views;
    private long user_id;
    private long category_id;

    public Post() {

    }
}
