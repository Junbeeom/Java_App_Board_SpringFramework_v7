package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;

import java.util.List;
import java.util.Optional;

public class JdbcTemplateBoardRepository implements BoardRepository{


    @Override
    public Board create(Board board) {
        return null;
    }

    @Override
    public Optional<Board> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Board> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Board> readAll() {
        return null;
    }

    @Override
    public Board update(Long boardId, Board updateParam) {
        return null;
    }

    @Override
    public Board delete(Long boardId) {
        return null;
    }
}
