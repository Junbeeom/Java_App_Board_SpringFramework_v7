//package com.example.SpringFramework.board.repository.mybatis;
//
//import com.example.SpringFramework.board.domain.board.Board;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//@Mapper
//public interface BoardMapper {
//    /**
//     *      Board create(Board board);
//     *     //id로 게시글 찾기
//     *     Optional<Board> findById(long id);
//     *     //name로 게시글 찾기
//     *     Optional<Board> findByName(String name);
//     *     List<Board> readAll();
//     *     Board update(Long boardId, Board updateParam);
//     *     Board delete(Long boardId);
//     */
//
//    void create(Board board);
//    Optional<Board> findById(Long id);
//    Optional<Board> findByName(String name);
//    List<Board> findAll(Long id);
//    //void update(@Param("id") Long id, @Param("updateParam ") );
//
//}
