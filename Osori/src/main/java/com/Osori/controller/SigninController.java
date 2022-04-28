package com.Osori.controller;

import com.Osori.dto.SigninReqDto;
import com.Osori.dto.UserResDto;
import com.Osori.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signin")
public class SigninController {
    private final SigninService signinService;

    @PostMapping
    public ResponseEntity<UserResDto> signin(@RequestBody SigninReqDto signinReqDto){
        return new ResponseEntity<>(signinService.Login(signinReqDto), HttpStatus.OK);
    }
}
