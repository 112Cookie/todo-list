package com.cookie.todolist.controller;

import com.cookie.todolist.vo.Credential;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class CredentialController {
    @Getter
    private static Credential credential;

    @PostMapping(value = "/givemesecret")
    public static Credential givemesecret(@Validated @NonNull @RequestBody Credential credential){
        CredentialController.credential = credential;
        return credential;
    }
}
