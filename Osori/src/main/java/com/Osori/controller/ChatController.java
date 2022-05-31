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
@RequestMapping("/chat/{userId}")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatResDto> getChats(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(chatService.getChats(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerResDto> sendChat(@PathVariable("userId") Long userId, @RequestBody AnswerReqDto answerReqDto){
        return new ResponseEntity<>(chatService.createAnswer(userId, answerReqDto), HttpStatus.OK);
    }
}
