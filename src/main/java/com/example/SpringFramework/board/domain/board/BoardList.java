package com.example.SpringFramework.board.domain.board;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BoardList {
    private Long board_no;
    private String tittle;
    private String name;
    private String created_ts;
    private boolean deleted;

    public BoardList() {}

    public BoardList(String tittle, String name, String created_ts, boolean is_deleted) {
        this.tittle = tittle;
        this.name = name;
        this.created_ts = created_ts;
        this.deleted = is_deleted;
    }
}
