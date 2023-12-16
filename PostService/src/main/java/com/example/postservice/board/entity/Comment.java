package com.example.postservice.board.entity;

import com.example.postservice.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment_table")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String commentWriter, String commentContents, Board board) {
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.board = board;
    }

    public static Comment toEntity(CommentDTO commentDTO, Board boardEntity) {
        return Comment.builder()
                .commentWriter(commentDTO.getCommentWriter())
                .commentContents(commentDTO.getCommentContents())
                .board(boardEntity)
                .build();
    }
}