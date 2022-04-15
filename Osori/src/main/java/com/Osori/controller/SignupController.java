package com.Osori.controller;

import com.Osori.dto.UserDto;
import com.Osori.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;

    @PostMapping
    public ResponseEntity<HttpStatus> signup(@RequestBody UserDto userDto){
        signupService.Register(userDto);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
