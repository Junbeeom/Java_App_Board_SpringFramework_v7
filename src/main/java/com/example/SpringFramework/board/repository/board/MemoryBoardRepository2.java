package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.repository.board.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryBoardRepository2 implements BoardRepository {

    private static HashMap<Long, Board> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Board create(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Board> findByName(String name) {
        return store.values().stream()
                .filter(board -> board.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Board> readAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Board update(Long boardId, Board updateParam) {
        Board findBoard = findById(boardId).get();
        findBoard.setTittle(updateParam.getTittle());
        findBoard.setContent(updateParam.getContent());
        findBoard.setName(updateParam.getName());


        return null;
    }

    @Override
    public Board delete(Long boardId, Board updateParam) {
        store.remove(boardId);

        return null;
    }

    public void clearStore() {
        store.clear();
    }
}
