package com.example.SpringFramework.board.domain.board;

import com.example.SpringFramework.board.domain.paging.Criteria;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class Board  {
    @NotNull
    private Long boardNo;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String name;
    @NotNull
    private String createdTs;
    @NotNull
    private String updatedTs;
    @NotNull
    private String deletedTs;
    @NotNull
    private String isDeleted;

    public Board() {
    }

    public Board(Long boardNo, String title, String content, String name, String createdTs, String updatedTs, String deletedTs, String isDeleted) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdTs = createdTs;
        this.updatedTs = updatedTs;
        this.deletedTs = deletedTs;
        this.isDeleted = isDeleted;
    }
}

