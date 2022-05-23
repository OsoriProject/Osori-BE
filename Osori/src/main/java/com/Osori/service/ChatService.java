package com.Osori.service;

import com.Osori.domain.entity.Chat;
import com.Osori.domain.entity.User;
import com.Osori.domain.repository.ChatRepository;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.*;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    private User getUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));
    }

    public ChatResDto getChats(Long userId){
        User user = getUser(userId);
        List<Chat> chatList = chatRepository.findByUser(user);
        return ChatResDto.of(chatList);
    }

    public AnswerResDto createAnswer(Long userId, AnswerReqDto answerReqDto){
        User user = getUser(userId);

        List<String> playlists = new ArrayList<>();
        String answer = answerReqDto.getContent() + "에 대한 대답";
        if(true){ // 앞에 분석 하는 부분 넣어서 조건에 달아줘야함, 생성 안할때
            ;
        }else{ // 분석 결과 플레이리스트를 생성할 때
            boolean flag = true;
            while(flag){
                playlists.add(Integer.toString(playlists.size()));
                if(playlists.size() > 5)
                    flag = false;
            }
        }

        chatRepository.save(Chat.builder()
                .content(answerReqDto.getContent())
                .sender("user")
                .user(user)
                .build());

        Long chatId = chatRepository.save(Chat.builder()
                .content(answer)
                .sender("bot")
                .user(user)
                .build()).getId();

        return AnswerResDto.of(answer, playlists, chatId);
    }

}
