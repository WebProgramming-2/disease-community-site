package com.example.mainpageservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Like {
    @Id
    private long id;
    private long user_id;
    private long post_id;

    public Like() {

    }
}
