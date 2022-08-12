package com.example.SpringFramework.board.validation;

import com.example.SpringFramework.board.domain.board.Board;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Board board = new Board();
        board.setTittle(" "); //공백
        board.setContent("123123");

        Set<ConstraintViolation<Board>> violations = validator.validate(board);
        for (ConstraintViolation<Board> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage());
        }
    }
}
