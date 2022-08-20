package com.example.SpringFramework.board.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Getter
@Setter
public class Member {
    //DB id
    private Long user_no;

    @NotEmpty
    @Email(message = "이메일 형식이 아닙니다.")
    private String id;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$", message = "//영문자+숫자+특수조합(8~25자리 입력)하세요.")
    private String pw;

    @NotEmpty
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]+$", message = "한글만 입력 가능합니다.")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "xxxx-xx-xx")
    private String birthdate; //db에는 datetime type

    @NotEmpty
    private String sex;

    @NotEmpty
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "xxx-xxxx-xxxx")
    private String phone;


    private Timestamp createdTs;
    private Timestamp updatedTs;
    private Timestamp deleted_ts;
    private String is_deleted;
}
