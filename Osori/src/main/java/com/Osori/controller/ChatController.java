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

    public static final Long tmpUserId = 1L; // 로그인 상태 관리 어떻게 하는지 결정 후 적용

    @GetMapping
    public ResponseEntity<ChatResDto> getChats(){
        return new ResponseEntity<>(chatService.getChats(tmpUserId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerResDto> sendChat(@RequestBody AnswerReqDto answerReqDto){
        return new ResponseEntity<>(chatService.createAnswer(tmpUserId, answerReqDto), HttpStatus.OK);
    }
}
