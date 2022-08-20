package com.example.SpringFramework.board.service.Board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.repository.board.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board create(Board board) {
       return boardRepository.create(board);
    }

    public Optional<Board> findById(long id) {
        return boardRepository.findById(id);
    }

    public Optional<Board> findByName(String name) {
        return boardRepository.findByName(name);
    }

    public List<Board> readAll() {
        return boardRepository.readAll();
    }

    public Board update(Long boardId, Board updateParam) {
        return boardRepository.update(boardId, updateParam);
    }

    public Board delete(Long boardId, Board updateParam) {
        return boardRepository.delete(boardId, Board updateParam);
    }

}
