package com.example.service_admin.controller;

import com.example.service_admin.dto.category.response.CategoryResponse;
import com.example.service_admin.dto.category.request.RequestUpdateCategory;
import com.example.service_admin.dto.category.response.InquiredCategoryResponse;
import com.example.service_admin.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administration")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService service){
        this.categoryService = service;
    }

    @PostMapping("/category")
    public void createCategory(@RequestParam String name){
        this.categoryService.createCategory(name);
    }

    @PutMapping("/category")
    public void updateCategory(@RequestBody RequestUpdateCategory request){
        this.categoryService.updateCategory(request);
    }

    @GetMapping("/category")
    public List<CategoryResponse> getCategory(){
        List<CategoryResponse> a = this.categoryService.getAllCategory();
        return a;
    }

    @GetMapping("/category")
    public void getCategory(@RequestParam String name){

    }

    @DeleteMapping("/category")
    public void deleteCategory(@RequestParam long id){
        // 홍정우씨에게 post, comment 삭제 부탁하기
    }
    @GetMapping("/inqcategory")
    public List<InquiredCategoryResponse> getInquiredCategory(){
        return this.categoryService.getInquiredCategory();
    }
}
