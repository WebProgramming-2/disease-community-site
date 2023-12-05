package com.example.postservice.board.entity;

import com.example.postservice.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Integer boardHits;

    @Builder
    public Board(Long id, String boardWriter, String boardPass, String boardTitle, String boardContents, int boardHits) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
    }

    public static Board toEntity(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits((boardDTO.getBoardHits() != null) ? boardDTO.getBoardHits() : 0)
                .build();
    }
}
