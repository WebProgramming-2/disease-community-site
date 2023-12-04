package com.example.postservice.board.service;

import com.example.postservice.board.dto.BoardDTO;
import com.example.postservice.board.entity.Board;
import com.example.postservice.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        Board board = Board.toEntity(boardDTO);
        boardRepository.save(board);
    }
}
