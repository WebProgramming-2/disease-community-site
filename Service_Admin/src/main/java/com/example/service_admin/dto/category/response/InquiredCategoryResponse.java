package com.example.service_admin.dto.category.response;

import com.example.service_admin.jpa.inquiredcategory.InquiredCategory;
import lombok.Getter;

@Getter
public class InquiredCategoryResponse {
    private long id;
    private String name;
    private long user_id;
    private String desc;

    public InquiredCategoryResponse(InquiredCategory inquiredCategory){
        this.id = inquiredCategory.getId();
        this.name = inquiredCategory.getName();
        this.user_id = inquiredCategory.getUser_id();
        this.desc = inquiredCategory.getDescription();
    }
}
