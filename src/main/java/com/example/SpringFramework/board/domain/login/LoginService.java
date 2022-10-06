package com.example.SpringFramework.board.domain.login;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId).filter(m -> m.getPw().equals(password))
                .orElse(null);
    }
}
