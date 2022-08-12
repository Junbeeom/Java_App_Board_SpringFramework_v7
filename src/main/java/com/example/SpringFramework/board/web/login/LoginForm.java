package com.example.SpringFramework.board.web.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {
    @NotEmpty
    private String id;
    @NotEmpty
    private String pw;
}
