package com.example.SpringFramework.board.service.Board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.paging.Criteria;
import com.example.SpringFramework.board.domain.board.paging.PaginationInfo;
import com.example.SpringFramework.board.repository.board.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public Board delete(Long boardId) {
        return boardRepository.delete(boardId);
    }

    public List<Board> findAll(Board params) {



        int boardTotalCount = boardRepository.totalCount(params);

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);

        System.out.println("보드 서비스에서 실행된 totalcount입니다." + boardTotalCount);
        if (boardTotalCount > 0) {
            return boardRepository.findAll(params);
        }
        return null;
    }
}
