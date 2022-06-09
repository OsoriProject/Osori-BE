package com.Osori.service;

import com.Osori.domain.entity.Chat;
import com.Osori.domain.entity.User;
import com.Osori.domain.repository.ChatRepository;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.*;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import com.Osori.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final TokenProvider tokenProvider;

    private final YoutubeService youtubeService;

    private User getUser(String userToken){
        String userToken_ = userToken.substring(7);
        String userEmail = tokenProvider.getUserEmailFromToken(userToken_);
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));
    }

    public ChatResDto getChats(String userToken){
        User user = getUser(userToken);
        List<Chat> chatList = chatRepository.findByUser(user);
        return ChatResDto.of(chatList);
    }

    public AnswerResDto createAnswer(String userToken, AnswerReqDto answerReqDto){
        User user = getUser(userToken);

        List<YoutubeDto> playlists = new ArrayList<>();
        String answer = answerReqDto.getContent() + "에 대한 대답";
        if(false){ // 앞에 분석 하는 부분 넣어서 조건에 달아줘야함, 생성 안할때
            ;
        }else{ // 분석 결과 플레이리스트를 생성할 때
//            boolean flag = true;
//            while(flag){
//                if(playlists.size() > 1)
//                    flag = false;
//                YoutubeDto youtubeDto = youtubeService.get(answerReqDto.getContent());
//                playlists.add(youtubeDto);
//            }

            YoutubeDto youtubeDto = youtubeService.get(answerReqDto.getContent());
            playlists.add(youtubeDto);
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
