package com.example.SpringFramework.board.repository.board;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.BoardList;
import com.example.SpringFramework.board.domain.paging.Criteria;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    // 게시글 생성
//    Board create(Board board);

    // 조회 및 검색
    List<Board> findAll(Criteria params);

    // 게시글 상세 조회
    //Optional<Board> findByNo(Long boardNo);

    // 게시글 전체 개수 조회
    int totalCount();

    // 게시글 수정
//    Board update(Long boardId, Board updateParam);

    // 게시글 삭제
    //Board delete(Long boardId);
}
