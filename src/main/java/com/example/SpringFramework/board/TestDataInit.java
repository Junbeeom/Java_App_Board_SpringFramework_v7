//package com.example.SpringFramework.board;
//
//import com.example.SpringFramework.board.domain.board.Board;
//import com.example.SpringFramework.board.domain.member.Member;
//import com.example.SpringFramework.board.repository.board.MemoryBoardRepository2;
//import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//@RequiredArgsConstructor
//public class TestDataInit {
//
//    private final MemoryBoardRepository2 memoryBoardRepository2;
//    private final MemoryMemberRepository memoryMemberRepository;
//
//    /**
//     * 테스트용 데이터 추가
//     */
//    @PostConstruct
//    public void init() {
//        memoryBoardRepository2.create(new Board("제목", "내용", "이름",
//                "없음", "없음", "없음"));
//        memoryBoardRepository2.create(new Board("제목2", "내용2", "이름2",
//                "없음2", "없음2", "없음2"));
//
//        Member member = new Member();
//        member.setId("test");
//        member.setPw("test!");
//        member.setName("테스터");
//        member.setBirthdate("950531");
//        member.setSex("남자");
//        member.setPhone("01026905031");
//
//       memoryMemberRepository.create(member);
//
//    }
//
//}