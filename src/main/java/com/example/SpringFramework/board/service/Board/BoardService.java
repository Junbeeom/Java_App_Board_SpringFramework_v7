package com.example.SpringFramework.board.service.Board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.paging.Criteria;
import com.example.SpringFramework.board.domain.paging.Pagination;
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

//    public Board create(Board board) {
//       return boardRepository.create(board);
//    }

    public List<Board> findAll(Board params) {

        int totalCount = boardRepository.totalCount();

        Pagination pagination = new Pagination(params, totalCount);

        if (totalCount != 0) {
            return boardRepository.findAll(params);
        }

        return null;
    }

//    public Optional<Board> findByNo(Long boardNo) {
//        return boardRepository.findByNo(boardNo);
//    }
//
//    public Board update(Long boardNo, Board updateParam) {
//        return boardRepository.update(boardNo, updateParam);
//    }
//
//    public Board delete(Long boardNo) {
//        return boardRepository.delete(boardNo);
//    }
}
