package com.example.SpringFramework.board.service.member;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemberRepository;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setId("조준범");
        member.setPw("조준범");
        member.setName("조준범");
        member.setBirthdate("조준범");
        member.setSex("조준범");
        member.setPhone("조준범");
        member.setCreatedTs(Timestamp.valueOf("1995-05-01 1:1:1"));


        //when
        Long saveUser_no = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveUser_no).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setId("조준범");
        member1.setPw("조준범");
        member1.setName("조준범");
        member1.setBirthdate("조준범");
        member1.setSex("조준범");
        member1.setPhone("조준범");
        member1.setCreatedTs(Timestamp.valueOf("1995-05-01 1:1:1"));

        Member member2 = new Member();
        member2.setId("조준범");
        member2.setPw("조준범");
        member2.setName("조준범");
        member2.setBirthdate("조준범");
        member2.setSex("조준범");
        member2.setPhone("조준범");
        member2.setCreatedTs(Timestamp.valueOf("1995-05-01 1:1:1"));

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }
}