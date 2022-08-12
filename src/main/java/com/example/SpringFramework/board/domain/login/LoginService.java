package com.example.SpringFramework.board.domain.login;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryMemberRepository memoryMemberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {

        return memoryMemberRepository.findByLoginId(loginId).filter(m -> m.getPw().equals(password))
                .orElse(null);
    }
}
