package com.example.mainpageservice.service;

import com.example.mainpageservice.domain.CategoryRepository;
import com.example.mainpageservice.domain.PostRepository;
import com.example.mainpageservice.dto.response.CategoryDTO;
import com.example.mainpageservice.dto.response.PostDTO;
import jakarta.servlet.http.Cookie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public MainPageService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PostDTO> getPopularPosts() {
        return postRepository.findPopularPosts();
    }

    public List<CategoryDTO> getCategoryList() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public Page<PostDTO> getPosts(String keyword, String searchType, String sortType, int userDisplay, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, userDisplay);
        Page<PostDTO> postPage;
        if ("title".equals(searchType)) {
            postPage = titleCaseSort(keyword, sortType, pageable);
        } else if ("content".equals(searchType)) {
            postPage = contentCaseSort(keyword, sortType, pageable);
        } else if ("nickname".equals(searchType)) {
            postPage = nicknameCaseSort(keyword, sortType, pageable);
        } else {
            postPage = postRepository.findByTitleContainingOrderByLikeCountDesc(keyword, pageable);
        }
        return postPage;
    }

    private Page<PostDTO> titleCaseSort(String keyword, String sortType, Pageable pageable) {
        if ("like".equals(sortType)) {
            return postRepository.findByTitleContainingOrderByLikeCountDesc(keyword, pageable);
        } else if ("views".equals(sortType)) {
            return postRepository.findByTitleContainingOrderByViewsDesc(keyword, pageable);
        } else if ("create_at".equals(sortType)) {
            return postRepository.findByTitleContainingOrderByCreateAtDesc(keyword, pageable);
        } else {
            return postRepository.findByTitleContainingOrderByLikeCountDesc(keyword, pageable);
        }
    }

    private Page<PostDTO> contentCaseSort(String keyword, String sortType, Pageable pageable) {
        if ("like".equals(sortType)) {
            return postRepository.findByContentContainingOrderByLikeCountDesc(keyword, pageable);
        } else if ("views".equals(sortType)) {
            return postRepository.findByContentContainingOrderByViewsDesc(keyword, pageable);
        } else if ("create_at".equals(sortType)) {
            return postRepository.findByContentContainingOrderByCreateAtDesc(keyword, pageable);
        } else {
            return postRepository.findByContentContainingOrderByLikeCountDesc(keyword, pageable);
        }
    }

    private Page<PostDTO> nicknameCaseSort(String keyword, String sortType, Pageable pageable) {
        if ("like".equals(sortType)) {
            return postRepository.findByNicknameContainingOrderByLikeCountDesc(keyword, pageable);
        } else if ("views".equals(sortType)) {
            return postRepository.findByNicknameContainingOrderByViewsDesc(keyword, pageable);
        } else if ("create_at".equals(sortType)) {
            return postRepository.findByNicknameContainingOrderByCreateAtDesc(keyword, pageable);
        } else {
            return postRepository.findByNicknameContainingOrderByLikeCountDesc(keyword, pageable);
        }
    }

    public String updateRecentSearches(String recentSearches, String keyword) {
        String[] searches = recentSearches.split("/");
        List<String> searchList = new ArrayList<>(Arrays.asList(searches));
        if (searchList.contains(keyword)) {
            searchList.remove(keyword);
            searchList.add(keyword);
        } else {
            searchList.add(keyword);
        }
        if (searchList.size() > 10) {
            searchList.remove(0);
        }
        return String.join("/", searchList);
    }

    public Cookie updateRecentSearchesCookie(String keyword, String recentSearches) {
        Cookie recentSearchesCookie;
        if (recentSearches == null) {
            recentSearchesCookie = new Cookie("recentSearches", keyword);
        } else {
            String updatedRecentSearches = updateRecentSearches(recentSearches, keyword);
            recentSearchesCookie = new Cookie("recentSearches", updatedRecentSearches);
        }
        return recentSearchesCookie;
    }
}
