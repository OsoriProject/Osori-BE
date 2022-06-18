package com.Osori.service;

import com.Osori.domain.entity.*;
import com.Osori.domain.repository.*;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final TokenProvider tokenProvider;

    private final YoutubeService youtubeService;

    private final R_MusicRepository r_musicRepository;
    private final PlaylistRepository playlistRepository;
    private final MusicRepository musicRepository;

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

    public ChatDto getChat(String userToken, Long chatId){
        User user = getUser(userToken);
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_CHAT));
        return ChatDto.of(chat);
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
            Optional<R_Music> music = r_musicRepository.findById(youtube);
            if(music.isPresent()){
                System.out.println("이미 존재");
                playlists.add(YoutubeDto.builder()
                        .videoId(music.get().getVideoId())
                        .title(music.get().getTitle())
                        .thumbnail(music.get().getThumbnail())
                        .build());
            }else {
                YoutubeDto youtubeDto = youtubeService.get(youtube);
                playlists.add(youtubeDto);
                r_musicRepository.save(R_Music.builder()
                        .id(youtube)
                        .videoId(youtubeDto.getVideoId())
                        .thumbnail(youtubeDto.getThumbnail())
                        .title(youtubeDto.getTitle())
                        .build());
            }
        }

        Playlist playlist = null;
        Long playlistId = null;
        String answer = queryDto.getPlaylist().isEmpty() ? "다시 요청해 주세요." : "추천드리는 플레이리스트 입니다.";
        if(!answer.equals("다시 요청해 주세요.")){
            playlist = playlistRepository.save(Playlist.builder()
                    .name("기본값")
                    .thumbnail(playlists.get(0).getThumbnail())
                    .user(null)
                    .build());
            playlistId = playlist.getId();

            List<Music> musicList = new ArrayList<>();
            for(YoutubeDto music:playlists){
                Music m = Music.builder()
                        .vid(music.getVideoId())
                        .title(music.getTitle())
                        .thumbnail(music.getThumbnail())
                        .playlist(playlist)
                        .build();
                musicList.add(m);
            }
            musicRepository.saveAll(musicList);
        }

        chatRepository.save(Chat.builder()
                .content(answerReqDto.getContent())
                .sender("user")
                .playlist(playlist)
                .user(user)
                .build());

        Long chatId = chatRepository.save(Chat.builder()
                .content(answer)
                .sender("bot")
                .playlist(playlist)
                .user(user)
                .build()).getId();

        return AnswerResDto.of(answer, playlists, chatId, playlistId);
    }

}
