package com.example.SpringFramework.board.web.member;

import com.example.SpringFramework.board.domain.member.Member;
import com.example.SpringFramework.board.repository.member.MemoryMemberRepository;
import com.example.SpringFramework.board.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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

    //등록 폼
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        System.out.println("여긴 등록홈 ");
        return "members/addMemberForm";
    }

    //등록 로직
    @PostMapping("/add")
    public String create(@Validated @ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/addMemberForm";
        }


        memberService.join(member);
        return "redirect:/";
    }

    //등록시 중복 확인
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id) {

        int result = memberService.validateDuplicateLoginId(id);
        return result;
    }
}
