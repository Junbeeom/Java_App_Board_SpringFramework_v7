package com.example.SpringFramework.board.web.basic;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.board.BoardUpdate;
import com.example.SpringFramework.board.domain.paging.Criteria;
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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor
public class BasicBoardController {

    private final BoardService boardService;

    //목록
    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardService.finAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }

//    //목록
//    @GetMapping
//    public String boards(@ModelAttribute Board criteria, Model model) {
//        List<Board> boards = boardService.findAll(criteria);
//        model.addAttribute("boards", boards);
//
//        return "basic/boards";
//    }



    //상품 상세
    @GetMapping("/{boardId}")
    public String board(@PathVariable Long boardNo, Model model) {
        //Board board = boardService.findByNo(boardNo).get();
        //model.addAttribute("board", board);
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
        board.setTitle(boardTittle);
        board.setContent(boardContent);
        board.setName(boardName);

       // boardService.create(board);

        //상품을 저장하고 나서 상세 화면을 보여주기 위해서
        //뷰를 직접 만들지 않고 board.html에 다 만들어 놨기 때문에 뿌리기만 하면 됨.
        model.addAttribute("board", board);
        return "basic/board";
    }

    //등록 로직
    @PostMapping("/add")
    public String add(@Validated  @ModelAttribute("board") BoardCreateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

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
        Board boardParam = new Board();
        boardParam.setTitle(form.getTittle());
        boardParam.setContent(form.getContent());
        boardParam.setName(form.getName());

        //Board createBoard = boardService.create(boardParam);
        //redirectAttributes.addAttribute("boardId", createBoard.getBoardNo());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/boards/{boardId}";


    }

    //수정 폼 보기.
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {

<<<<<<< HEAD
<<<<<<< HEAD


        //Board board = boardService.findByNo(boardId).get();
        //model.addAttribute("board", board);
=======
=======
>>>>>>> parent of 190f94f (before code review)
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
>>>>>>> parent of 190f94f (before code review)
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
        boardParam.setBoardNo(form.getId());
        boardParam.setTitle(form.getTittle());
        boardParam.setContent(form.getContent());
        boardParam.setName(form.getName());

        //boardService.update(boardId, boardParam);

        return "redirect:/basic/boards/{boardId}";
    }

    //삭제
    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId, Model model) {


        //boardService.delete(boardId);

        return "redirect:/basic/boards";
    }
}
