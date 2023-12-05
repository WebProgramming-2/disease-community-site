package com.example.postservice.board.service;

import com.example.postservice.board.dto.BoardDTO;
import com.example.postservice.board.entity.Board;
import com.example.postservice.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        Board board = Board.toEntity(boardDTO);
        boardRepository.save(board);
    }

    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().
                map(BoardDTO::toBoardDTO)
                .toList();
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return BoardDTO.toBoardDTO(board);
        }
        return null;
    }

    public BoardDTO update(BoardDTO boardDTO) {
        Board board = Board.toEntity(boardDTO);
        boardRepository.save(board);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        int pageLimit = 1;
        Page<Board> boards =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        return boards.map(board -> BoardDTO.builder()
                .id(board.getId())
                .boardWriter(board.getBoardWriter())
                .boardTitle(board.getBoardTitle())
                .boardHits(board.getBoardHits())
                .boardCreatedTime(board.getCreatedTime())
                .build());
    }
}
