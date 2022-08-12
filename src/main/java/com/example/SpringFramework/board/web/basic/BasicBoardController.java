package com.example.SpringFramework.board.web.basic;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.repository.MemoryBoardRepository2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor // final붙은 생성자 만들어줌. (각각의 생성자가 하나 일 경우.)
public class BasicBoardController {

    private final MemoryBoardRepository2 memoryBoardRepository2;
    private final BoardValidator boardValidator;

    //basicBoardContoller가 호출이 될때 마다 생성된다. 호출되서 검증해준다.
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(boardValidator);
    }

    //목록
    @GetMapping
    public String boards(Model model) {
        List<Board> boards = memoryBoardRepository2.readAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }

    //상품 상세
    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = memoryBoardRepository2.findById(boardId).get();
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

        memoryBoardRepository2.create(board);

        //상품을 저장하고 나서 상세 화면을 보여주기 위해서
        //뷰를 직접 만들지 않고 board.html에 다 만들어 놨기 때문에 뿌리기만 하면 됨.
        model.addAttribute("board", board);
        return "basic/board";
    }

    //등록 로직
    @PostMapping("/add")
    public String add(@Validated  @ModelAttribute Board board, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        /**
         *  boardValidator.validate(board, bindingResult); > @Validated로 치환 가능.
         */
//        //검증기
//        boardValidator.validate(board, bindingResult);

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            //같이 넘어감으로 model.attiribute는 생략 가능.
            log.info("errors={}", bindingResult);
            return "basic/addForm";
        }

        //성공로직
        Board createBoard = memoryBoardRepository2.create(board);
        redirectAttributes.addAttribute("boardId", createBoard.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/boards/{boardId}";


    }

    //수정 폼 보기.
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {

        Board board = memoryBoardRepository2.findById(boardId).get();
        model.addAttribute("board", board);
        return "basic/editForm";

    }

    //수정
    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute Board board) {
        System.out.println("현재 수정 로직 진입");
        memoryBoardRepository2.update(boardId, board);

        return "redirect:/basic/boards/{boardId}";
    }

    //삭제
    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId, Model model) {


        System.out.println(boardId);
        memoryBoardRepository2.delete(boardId);

        return "redirect:/basic/boards";
    }




    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memoryBoardRepository2.create(new Board("제목", "내용", "이름",
                "없음", "없음", "없음"));
        memoryBoardRepository2.create(new Board("제목2", "내용2", "이름2",
                "없음2", "없음2", "없음2"));
    }


}
