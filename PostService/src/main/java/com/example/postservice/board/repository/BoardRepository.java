package com.example.postservice.board.repository;

import com.example.postservice.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query(value = "UPDATE Board b SET b.boardHits = b.boardHits + 1 WHERE b.id=:id")
    void updateHits(@Param("id") Long id);
}