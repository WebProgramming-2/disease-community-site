package com.example.postservice.board.entity;

import com.example.postservice.board.entity.BaseEntity;
import com.example.postservice.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static BoardFile toBoardFileEntity(Board boardEntity, String originalFileName, String storedFileName) {
        BoardFile boardFile = new BoardFile();
        boardFile.setOriginalFileName(originalFileName);
        boardFile.setStoredFileName(storedFileName);
        boardFile.setBoard(boardEntity);
        return boardFile;
    }
}