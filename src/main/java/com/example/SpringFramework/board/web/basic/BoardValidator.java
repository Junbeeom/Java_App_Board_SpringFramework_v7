package com.example.SpringFramework.board.web.basic;

import com.example.SpringFramework.board.domain.board.Board;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//빈으로 등록하기 위해 컴포넌트, > basicBoardcontroller에 생성자 주입을 해야한다.
@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Board board = (Board) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "tittle", "required");

        //
        //검증 로직
        if(!StringUtils.hasText(board.getTitle())) {
            errors.rejectValue("tittle", "required");
        }

        if(board.getContent() == null || board.getContent().length() >= 3 ) {
            errors.rejectValue("content", "max", new Object[]{3}, null);
        }

    }
}
