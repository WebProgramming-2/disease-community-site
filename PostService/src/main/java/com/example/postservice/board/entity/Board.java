package com.example.postservice.board.entity;

import com.example.postservice.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static Board toEntity(BoardDTO boardDTO) {
        return Board.builder()
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(boardDTO.getBoardHits())
                .build();
    }
}
