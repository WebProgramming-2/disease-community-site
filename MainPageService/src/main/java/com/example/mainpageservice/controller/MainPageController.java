package com.example.mainpageservice.controller;

import com.example.mainpageservice.dto.response.CategoryDTO;
import com.example.mainpageservice.dto.response.PostsResponseDTO;
import com.example.mainpageservice.dto.response.PostDTO;
import com.example.mainpageservice.service.MainPageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mainPage-service")
public class MainPageController {
    private final MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/popular-posts")
    public @ResponseBody List<PostDTO> getPopularPosts() {
        return mainPageService.getPopularPosts();
    }

    @GetMapping("/category-list")
    public @ResponseBody List<CategoryDTO> getCategoryList() {
        return mainPageService.getCategoryList();
    }

    @GetMapping("/post")
    public @ResponseBody PostsResponseDTO getPosts(
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

    @GetMapping("/")
    public String getMainPage(Model model,
                              @CookieValue(value = "recentSearches", required = false) String recentSearches
    ) {
        model.addAttribute("categoryList", this.getCategoryList());
        model.addAttribute("popularPosts", this.getPopularPosts());
        if(recentSearches==null) {recentSearches = "";}
        model.addAttribute("recentSearches", new ArrayList<>(Arrays.asList(recentSearches.split("/"))));
        return "MainPage";
    }

    @GetMapping("/posts")
    public String getSearchPage(Model model,
                                @RequestParam(name = "keyword") String keyword,
                                @RequestParam(name = "searchType", defaultValue = "title") String searchType,
                                @RequestParam(name = "sortType", defaultValue = "like") String sortType,
                                @RequestParam(name = "userDisplay", defaultValue = "10") int userDisplay,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                HttpServletResponse response,
                                @CookieValue(value = "recentSearches", required = false) String recentSearches
    ) {
        model.addAttribute("categoryList", this.getCategoryList());
        model.addAttribute("posts", this.getPosts(keyword, searchType, sortType, userDisplay, pageNum, response, recentSearches));
        if(recentSearches==null) {recentSearches = "";}
        model.addAttribute("recentSearches", new ArrayList<>(Arrays.asList(recentSearches.split("/"))));
        return "SearchPage";
    }
}
