package com.example.SpringFramework.board.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
public class Member {
    //DB id
    private Long user_no;
    @NotEmpty
    private String id;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String name;
    @NotEmpty
    private String birthdate; //db에는 datetime type
    @NotEmpty
    private String sex;
    @NotEmpty
    private String phone;


    private Timestamp createdTs;
    private Timestamp updatedTs;
    private Timestamp deleted_ts;
    private String isDeleted;
}
