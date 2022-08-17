package com.example.SpringFramework.board;

import com.example.SpringFramework.board.repository.member.MemberRepository;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import com.example.SpringFramework.board.service.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository() );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
