package com.example.SpringFramework.board.web.member;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import com.example.SpringFramework.board.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    //private final MemoryMemberRepository memoryMemberRepository;
//
//    /**
//     * DI를 지키기 위해서 기존 memoryMemberRepository로 추가 하는것이 아닌 Service > 생성자 주입을 통해서 주입 해주는 방식으로
//     * 수정을 하기 위한 로직 추가..
//     *
//     */
//
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String create(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        memberService.join(member);
        return "redirect:/";

    }
}
