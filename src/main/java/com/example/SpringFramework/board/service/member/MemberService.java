package com.example.SpringFramework.board.service.member;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemberRepository;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //중복 회원 검증.
        validateDuplicateMember(member);
        memberRepository.create(member);
        return member.getUser_no();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //중복 회원 검사 메소드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
}
