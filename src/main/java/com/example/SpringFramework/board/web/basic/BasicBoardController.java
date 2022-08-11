package com.example.SpringFramework.board.web.basic;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.repository.MemoryBoardRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor // final붙은 생성자 만들어줌.
public class BasicBoardController {

    private final MemoryBoardRepository2 memoryBoardRepository2;

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
    public String addForm() {
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

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("board") Board board, Model model) {
        System.out.println(board.getName());
        System.out.println(board.getContent());
        System.out.println("********************************");
        memoryBoardRepository2.create(board);
        //model.addAttribute("board", board);//자동추가, 생략가능 " "이 " " 대체
        System.out.println(board.getId());
        System.out.println(board.getName());
        System.out.println(board.getContent());
        System.out.println("********************************");

        return "basic/board";
    }

    //수정 폼 보기.
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {

        System.out.println("너는 언제 호출되니? getMapping");
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
