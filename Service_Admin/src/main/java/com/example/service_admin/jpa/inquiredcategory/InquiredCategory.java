package com.example.service_admin.jpa.inquiredcategory;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class InquiredCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name = null;

    //user랑 join하는데, 그걸 다른 사람거 요청으로 얻어내야하나?
    private Long user_id = null;

    private String description = null;

    protected InquiredCategory(){}

    public InquiredCategory(String name){
        if (name==null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
    }
}
