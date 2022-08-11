package com.example.SpringFramework.board.domain.board;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Board {
    private Long id;
    private String tittle;
    private String content;
    private String name;
    private String created_ts;
    private String updated_ts;
    private String deleted_ts;

    public Board() {
    }

    public Board(String tittle, String content, String name, String created_ts, String updated_ts, String deleted_ts) {
        this.tittle = tittle;
        this.content = content;
        this.name = name;
        this.created_ts = created_ts;
        this.updated_ts = updated_ts;
        this.deleted_ts = deleted_ts;
    }
}
