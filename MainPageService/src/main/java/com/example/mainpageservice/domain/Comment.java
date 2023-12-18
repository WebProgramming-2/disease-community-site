package com.example.mainpageservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id
    private long id;
    private String content;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private long post_id;

    public Comment() {

    }
}
