package com.example.mainpageservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Category {
    @Id
    private long id;
    private String name;
    private long parent_category_id;

    public Category() {

    }
}
