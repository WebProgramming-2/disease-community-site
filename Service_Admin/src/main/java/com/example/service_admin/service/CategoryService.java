package com.example.service_admin.service;

import com.example.service_admin.dto.category.response.CategoryResponse;
import com.example.service_admin.dto.category.request.RequestUpdateCategory;
import com.example.service_admin.dto.category.response.InquiredCategoryResponse;
import com.example.service_admin.jpa.category.Category;
import com.example.service_admin.jpa.category.CategoryRepository;
import com.example.service_admin.jpa.inquiredcategory.InquiredCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final InquiredCategoryRepository inqCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, InquiredCategoryRepository inqCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.inqCategoryRepository = inqCategoryRepository;
    }
    @Transactional
    public void createCategory(String name) {
        this.categoryRepository.save(new Category(name));
    }
    @Transactional
    public List<CategoryResponse> getAllCategory() {
        return this.categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category))
                        .collect(Collectors.toList());

    }
    @Transactional
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        categoryRepository.delete(category);
    }

    @Transactional
    public void updateCategory(RequestUpdateCategory request) {
        Category category = this.categoryRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        Category check = this.categoryRepository.findByName(request.getName());
        if(check == null)
            throw new IllegalArgumentException("Name exists.");

        category.setName(request.getName());
        categoryRepository.save(category);
    }

    @Transactional
    public List<InquiredCategoryResponse> getInquiredCategory() {
        return this.inqCategoryRepository.findAll().stream()
                .map(InquiredCategoryResponse::new)
                .collect(Collectors.toList());
    }
}
