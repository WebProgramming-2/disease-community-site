package com.example.mainpageservice.controller;

import com.example.mainpageservice.dto.response.CategoryDTO;
import com.example.mainpageservice.dto.response.PostsResponseDTO;
import com.example.mainpageservice.dto.response.PostDTO;
import com.example.mainpageservice.service.MainPageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainPageController {
    private final MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/popular-posts")
    public List<PostDTO> getPopularPosts() {
        return mainPageService.getPopularPosts();
    }

    @GetMapping("/category-list")
    public List<CategoryDTO> getCategoryList() {
        return mainPageService.getCategoryList();
    }

    @GetMapping("/posts")
    public PostsResponseDTO getPosts(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "searchType", defaultValue = "title") String searchType,
            @RequestParam(name = "sortType", defaultValue = "like") String sortType,
            @RequestParam(name = "userDisplay", defaultValue = "2") int userDisplay,
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            HttpServletResponse response,
            @CookieValue(value = "recentSearches", required = false) String recentSearches
    ) {
        response.addCookie(mainPageService.updateRecentSearchesCookie(keyword, recentSearches));
        Page<PostDTO> postPage = mainPageService.getPosts(keyword, searchType, sortType, userDisplay, pageNum - 1);
        return new PostsResponseDTO(postPage.getContent(), postPage.getNumber() + 1, postPage.getTotalPages(), postPage.getSize(), postPage.getTotalElements());
    }
}
