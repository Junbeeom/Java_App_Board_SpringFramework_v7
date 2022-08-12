package com.example.SpringFramework.board.web.basic.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BoardUpdateForm {
    @NotNull
    private Long id;

    @NotBlank
    private String tittle;

    @NotNull
    @Length(max = 3)
    private String content;

    private String name;

    private String updated_ts;
}