package com.example.service_admin.jpa.category;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name = null;


    protected Category(){}

    public Category(String name){
        if (name==null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
