package com.example.mainpageservice.dto.response;

import com.example.mainpageservice.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private long id;
    private String name;
    private long parent_category_id;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parent_category_id = category.getParent_category_id();
    }
}
