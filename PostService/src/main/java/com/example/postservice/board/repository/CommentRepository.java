package com.example.postservice.board.repository;

import com.example.postservice.board.entity.Board;
import com.example.postservice.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
