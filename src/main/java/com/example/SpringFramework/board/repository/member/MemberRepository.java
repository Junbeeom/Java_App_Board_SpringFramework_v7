package com.example.SpringFramework.board.repository.member;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member create(Member member);
    Optional<Member> findById(long user_no);
    Optional<Member> findByName(String name);
    Optional<Member> findByLoginId(String id);
    List<Member> findAll();
}
