package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.paging.Criteria;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board create(Board board);
    //id로 게시글 찾기
    Optional<Board> findById(long id);
    //name로 게시글 찾기
    Optional<Board> findByName(String name);
    List<Board> readAll();

    //페이지 네이션이 들어간 조회
    List<Board> findAll(Board params);

    int totalCount(Board params);

    Board update(Long boardId, Board updateParam);
    Board delete(Long boardId);
}
