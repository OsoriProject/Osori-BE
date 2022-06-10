package com.Osori.controller;

import com.Osori.dto.AnswerReqDto;
import com.Osori.dto.AnswerResDto;
import com.Osori.dto.ChatResDto;
import com.Osori.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatResDto> getChats(@RequestHeader(value = "Authorization") String userToken){
        return new ResponseEntity<>(chatService.getChats(userToken), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerResDto> sendChat(@RequestHeader(value = "Authorization") String userToken, @RequestBody AnswerReqDto answerReqDto){
        return new ResponseEntity<>(chatService.createAnswer(userToken, answerReqDto), HttpStatus.OK);
    }
}
