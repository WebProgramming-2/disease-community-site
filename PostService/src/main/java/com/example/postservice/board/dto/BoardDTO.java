package com.example.postservice.board.dto;

import com.example.postservice.board.entity.Board;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private Integer boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    @Builder
    public BoardDTO(Long id, String boardWriter, String boardTitle, String boardContents, Integer boardHits, LocalDateTime boardCreatedTime, LocalDateTime boardUpdatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
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
                .boardContents(board.getBoardContents())
                .boardTitle(board.getBoardTitle())
                .boardHits(board.getBoardHits())
                .boardCreatedTime(board.getCreatedTime())
                .boardUpdatedTime(board.getUpdatedTime())
                .build();
    }
}
