package com.example.SpringFramework.board.web;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import com.example.SpringFramework.board.service.member.MemberService;
import com.example.SpringFramework.board.web.argumentresolver.Login;
import com.example.SpringFramework.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    //private final MemoryMemberRepository memoryMemberRepository;
    private final MemberService memberService;
    private final SessionManager sessionManager;


    //@GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")

    public String homeLogin(@Login Member loginMember, Model model) {
        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}
