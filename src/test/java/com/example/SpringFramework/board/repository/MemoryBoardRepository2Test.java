package com.example.SpringFramework.board.repository;

import com.example.SpringFramework.board.domain.board.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBoardRepository2Test {

    MemoryBoardRepository2 memoryBoardRepository2 = new MemoryBoardRepository2();

    @AfterEach
    public void afterEach() {
        memoryBoardRepository2.clearStore();

    }


    @Test
    void create() {
        Board board = new Board();
        board.setName("조준범");
        board.setTittle("Frame Work 개발 시작");
        board.setContent("아직 갈길이 멀지만 하루하루 최선을 다한다.");

        memoryBoardRepository2.create(board);

        Board result = memoryBoardRepository2.findById(board.getId()).get();
        Assertions.assertEquals(board, result);
    }

    @Test
    void findById() {
        Board board2 = new Board();

    }

    @Test
    void findByName() {
        Board board1 = new Board();
        board1.setName("정이준");
        memoryBoardRepository2.create(board1);

        Board result = memoryBoardRepository2.findByName("정이준").get();
        Assertions.assertEquals(board1, result);
    }

    @Test
    void readAll() {
        Board board1 = new Board();
        board1.setName("조준범");
        memoryBoardRepository2.create(board1);

        Board board2 = new Board();
        board1.setName("정이준");
        memoryBoardRepository2.create(board2);

        List<Board> result = memoryBoardRepository2.readAll();

        Assertions.assertEquals(result.size(), 2);
        org.assertj.core.api.Assertions.assertThat(2).isEqualTo(result.size());

    }

    @Test
    void update() {
        Board board = new Board();
        board.setName("조준범");
        board.setTittle("사랑해");
        board.setContent("사랑해 사랑해");

        Board createBoard = memoryBoardRepository2.create(board);
        Long boardId = createBoard.getId();

        Board updateBoard = new Board();
        updateBoard.setName("민해주");
        updateBoard.setContent("좋아해 좋아해");
        updateBoard.setTittle("좋아해");
        memoryBoardRepository2.update(boardId, updateBoard);

        Board findBoard = memoryBoardRepository2.findById(boardId).get();

        Assertions.assertEquals(findBoard.getName(), updateBoard.getName());
        Assertions.assertEquals(findBoard.getContent(), updateBoard.getContent());
        Assertions.assertEquals(findBoard.getTittle(), updateBoard.getTittle());
    }

    @Test
    void delete() {
    }
}