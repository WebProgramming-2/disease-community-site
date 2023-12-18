package com.example.mainpageservice.domain;

import com.example.mainpageservice.dto.response.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(l.id) DESC " +
            "LIMIT 10")
    List<PostDTO> findPopularPosts();

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.title LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY COUNT(l.id) DESC")
    Page<PostDTO> findByTitleContainingOrderByLikeCountDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.title LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.views DESC")
    Page<PostDTO> findByTitleContainingOrderByViewsDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.title LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.create_at DESC")
    Page<PostDTO> findByTitleContainingOrderByCreateAtDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.content LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY COUNT(l.id) DESC")
    Page<PostDTO> findByContentContainingOrderByLikeCountDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.content LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.views DESC")
    Page<PostDTO> findByContentContainingOrderByViewsDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE p.content LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.create_at DESC")
    Page<PostDTO> findByContentContainingOrderByCreateAtDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE u.nickname LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY COUNT(l.id) DESC")
    Page<PostDTO> findByNicknameContainingOrderByLikeCountDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE u.nickname LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.views DESC")
    Page<PostDTO> findByNicknameContainingOrderByViewsDesc(String keyword, Pageable pageable);

    @Query("SELECT new com.example.mainpageservice.dto.response.PostDTO(p.title, p.create_at, p.views, COUNT(l.id), u.nickname, c.name, COUNT(cmt.id)) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON p.id = l.post_id " +
            "LEFT JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Category c ON p.category_id = c.id " +
            "LEFT JOIN Comment cmt ON p.id = cmt.post_id " +
            "WHERE u.nickname LIKE %:keyword% " +
            "GROUP BY p.id, p.title, p.create_at, p.views, u.nickname, c.name " +
            "ORDER BY p.create_at DESC")
    Page<PostDTO> findByNicknameContainingOrderByCreateAtDesc(String keyword, Pageable pageable);
}
