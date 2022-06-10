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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final TokenProvider tokenProvider;

    private final YoutubeService youtubeService;

    @Value("${flask.url}")
    private String flaskUrl;

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

        WebClient webClient = WebClient.builder()
                .baseUrl(flaskUrl)
                .build();

        QueryDto queryDto = webClient.post()
                .uri("/chat")
                .body(Mono.just(answerReqDto), AnswerReqDto.class)
                .retrieve()
                .bodyToMono(QueryDto.class)
                .block();

        for(String youtube : queryDto.getPlaylist()){
            YoutubeDto youtubeDto = youtubeService.get(youtube);
            playlists.add(youtubeDto);
        }

        String answer = queryDto.getPlaylist().isEmpty() ? "다시 요청해 주세요." : "추천드리는 플레이리스트 입니다.";

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
