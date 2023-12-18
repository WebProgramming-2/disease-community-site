package com.example.service_admin.dto.category.response;

import com.example.service_admin.jpa.category.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private long id;
    private String name;

    public CategoryResponse(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }

}
