package com.example.postservice.board.dto;

import com.example.postservice.board.entity.Board;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private Integer boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    @Builder
    public BoardDTO(Long id, String boardWriter, String boardPass, String boardTitle, String boardContents, Integer boardHits, LocalDateTime boardCreatedTime, LocalDateTime boardUpdatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
        this.boardUpdatedTime = boardUpdatedTime;
    }

    public static BoardDTO toBoardDTO(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .boardWriter(board.getBoardWriter())
                .boardPass(board.getBoardPass())
                .boardContents(board.getBoardContents())
                .boardTitle(board.getBoardTitle())
                .boardHits(board.getBoardHits())
                .boardCreatedTime(board.getCreatedTime())
                .boardUpdatedTime(board.getUpdatedTime())
                .build();
    }
}
