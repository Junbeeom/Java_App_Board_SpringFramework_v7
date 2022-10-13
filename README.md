# # Java_App_Board_SpringFramework_v7

# 1.프로젝트 개요
### 1.1 프로젝트 목적
- Spring Framework, Spring Boot를 활용한 게시판 Application 개발

### 1.2 목표 및 의의
#### 1.2.1 Java_App_Board_SpringFramework_v7
- Servlet에 대한 이해 
- Spring Framework의 이해 
- DispatcherServlet의 이해 
- 스프링 AOP에 대한 이해 
- 스프링 필터 및 인터셉터에 대한 이해


# 2. 개발 환경
- IntelliJ IDEA(Ultimate Edition), GitHub


# 3. 사용기술
- Java 11, Spring Boot, JavaScript, Thymeleaf 

# 4.프로젝트 설계

### 4.1 로그인 (Login page) 
<img width="597" alt="스크린샷 2022-10-07 오후 8 22 07" src="https://user-images.githubusercontent.com/103010985/194541904-2315eaf0-bdfd-4b3a-bc6f-e5df60bf8083.png">


### 4.2 회원가입 (id 중복 확인)
<img width="683" alt="스크린샷 2022-10-07 오후 8 22 32" src="https://user-images.githubusercontent.com/103010985/194541971-329fa2b4-ac8b-4c0b-b698-5ee76f6b24b0.png">

<img width="621" alt="스크린샷 2022-10-07 오후 8 22 59" src="https://user-images.githubusercontent.com/103010985/194542026-e86337bb-0385-4d1f-a6a7-9b5b49453f33.png">

### 4.2 게시판 (board page) 
<img width="715" alt="스크린샷 2022-10-07 오후 8 24 12" src="https://user-images.githubusercontent.com/103010985/194542300-c8452639-5dff-4dc7-8fee-3ddedd86380e.png">


### 4.2 게시글 작성하기
<img width="654" alt="스크린샷 2022-10-12 오후 7 22 58" src="https://user-images.githubusercontent.com/103010985/195318404-aecf7471-4cb7-431d-ae32-c682ab38738f.png">

### 4.3 게시글 상세 보기 
<img width="644" alt="스크린샷 2022-10-12 오후 7 23 48" src="https://user-images.githubusercontent.com/103010985/195318583-462aab4e-6506-4d74-8f61-cc4c59869166.png">

### 4.4 검색하기
<img width="712" alt="스크린샷 2022-10-12 오후 7 24 43" src="https://user-images.githubusercontent.com/103010985/195318808-795dbadf-0442-470b-9ed9-7e8c0c27343d.png">

### 4.5 수정하기
<img width="621" alt="스크린샷 2022-10-12 오후 7 24 05" src="https://user-images.githubusercontent.com/103010985/195318650-6b79f623-26bf-4458-884f-467604e27602.png">


### 4.5 board Package
<img width="810" alt="스크린샷 2022-10-12 오후 7 29 14" src="https://user-images.githubusercontent.com/103010985/195319785-e71d1254-f612-4983-bab3-58305a2a920f.png">


### 4.6 member Package
<img width="1581" alt="스크린샷 2022-10-12 오후 7 30 49" src="https://user-images.githubusercontent.com/103010985/195320088-197b1a0c-9d1a-4bcd-b355-7b42c47a8234.png">


# 5.기본 기능
- 등록 registered 
- 조회 listed
- 검색 searched
- 수정 modified
- 삭제 deleted



# 6.핵심 기능

### 6.1 Board Controller

```java
package com.example.SpringFramework.board.web.basic;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.SearchValue;
import com.example.SpringFramework.board.domain.board.paging.Criteria;
import com.example.SpringFramework.board.repository.board.BoardRepository;
import com.example.SpringFramework.board.repository.board.MemoryBoardRepository2;
import com.example.SpringFramework.board.service.Board.BoardService;
import com.example.SpringFramework.board.web.basic.form.BoardCreateForm;
import com.example.SpringFramework.board.web.basic.form.BoardUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor 
public class BasicBoardController {

    private final BoardService boardService;

    //
    @GetMapping
    public String boards(@ModelAttribute("params") Board params, Model model) {

        List<Board> boards = boardService.findAll(params);

        System.out.println("컨트로러에서 실행된 boards의 게시글 총 수 입니다 " + boards.size());
        model.addAttribute("boards", boards);

        return "basic/boards";
    }



    //상품 상세
    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "basic/board";
    }

    //등록 폼
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save(@RequestParam String boardTittle,
                       @RequestParam String boardContent,
                       @RequestParam String boardName,
                       Model model) {

        Board board = new Board();
        board.setTittle(boardTittle);
        board.setContent(boardContent);
        board.setName(boardName);

        boardService.create(board);

        model.addAttribute("board", board);
        return "basic/board";
    }

    //등록 로직
    @PostMapping("/add")
    public String add(@Validated  @ModelAttribute("board") BoardCreateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            //같이 넘어감으로 model.attiribute는 생략 가능.
            log.info("errors={}", bindingResult);
            return "basic/addForm";
        }

        //성공로직
        Board boardParam = new Board();
        boardParam.setTittle(form.getTittle());
        boardParam.setContent(form.getContent());
        boardParam.setName(form.getName());

        Board createBoard = boardService.create(boardParam);
        redirectAttributes.addAttribute("boardId", createBoard.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/boards/{boardId}";


    }

    //수정 폼 보기.
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {



        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "basic/editForm";

    }

    //수정
    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @Validated @ModelAttribute("board") BoardUpdateForm form, BindingResult bindingResult) {

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            //같이 넘어감으로 model.attiribute는 생략 가능.
            log.info("errors={}", bindingResult);
            return "basic/editForm";
        }

        //성공 로직
        Board boardParam = new Board();
        boardParam.setId(form.getId());
        boardParam.setTittle(form.getTittle());
        boardParam.setContent(form.getContent());
        boardParam.setName(form.getName());

        boardService.update(boardId, boardParam);

        return "redirect:/basic/boards/{boardId}";
    }

    //삭제
    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId, Model model) {


        boardService.delete(boardId);

        return "redirect:/basic/boards";
    }

    //검색
    @ModelAttribute("searchValue")
    public List<SearchValue> searchValues() {
        List<SearchValue> searchValues = new ArrayList<>();
        searchValues.add(new SearchValue("title",  "제목"));
        searchValues.add(new SearchValue("content", "내용"));
        searchValues.add(new SearchValue("name", "이름"));

        return searchValues;
    }

}

```

### 6.2 member Controller

```java
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
```



# 7.회고

### Java_App_Board_SpringFramework_v7

1. Spring MVC Pattern에서의 핵심은 DispatcherServlet이기에 MVC2 Pattern에 의거하여 domain, repository, service, web으로 나누었으며, SOLID 원칙 중 단일 책임 원칙과 의존성 역전 원칙을 지키고자 web은 domain을 의존하지만 domain은 web을 의존하지 않도록 설계했습니다.

2. Spring Boot에서 공식적으로 지원하고 권장하는 템플릿 엔진인 Thymeleaf를 사용하여 서버에서 HTML을 동적으로 렌더링 할 수 있도록 구현했습니다.

3. Form 데이터를 전달할때 별도의 객체를 사용하였으며, 클라이언트에서 유효성 검증을 위해 Bean Validation 기술 표준을 이용함으로써 스프링 전용 검증 Annotaion인 @Validated를 사용하여 효율성 함양했습니다.

4. 이전 JSP 프로젝트에서 nav를 모듈화하여 로그인 체크 및 redirect를 하였는데 Spring Boot Interceptor를 활용하여 Controller 호출 전 호출 되는 preHandle Method를 이용하여 보다 편리하고 효율적으로 로그인 체크 및 redirect가 일어나도록 개선하였습니다.



