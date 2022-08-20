package com.example.SpringFramework.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
public class Board {

    private Long id;

    private String tittle;

    private String content;

    private String name;
    private String created_ts;
    private String updated_ts;
    private String deleted_ts;
    private String is_deleted;

    public Board() {
    }

    public Board(String tittle, String content, String name, String created_ts, String updated_ts, String deleted_ts) {
        this.tittle = tittle;
        this.content = content;
        this.name = name;
        this.created_ts = created_ts;
        this.updated_ts = updated_ts;
        this.deleted_ts = deleted_ts;
        this.is_deleted = is_deleted;
    }
}
