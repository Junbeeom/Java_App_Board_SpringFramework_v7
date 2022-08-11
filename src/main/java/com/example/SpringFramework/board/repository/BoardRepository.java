package com.example.SpringFramework.board.repository;

import com.example.SpringFramework.board.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board create(Board board);
    //id로 게시글 찾기
    Optional<Board> findById(long id);

    //name로 게시글 찾기
    Optional<Board> findByName(String name);
    List<Board> readAll();
    Board update(Long boardId, Board updateParam);
    Board delete(Long boardId);
}
