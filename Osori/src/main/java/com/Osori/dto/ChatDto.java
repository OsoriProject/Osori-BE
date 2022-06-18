package com.Osori.dto;

import com.Osori.domain.entity.Chat;
import com.Osori.domain.entity.Music;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long id;
    private String content;
    private String sender;
    private Long playlistId;
    private List<MusicDto> playlist;

    public static ChatDto of(Chat chat){
        List<MusicDto> musics = new ArrayList<>();
        for(Music music : chat.getPlaylist().getMusics()){
            musics.add(MusicDto.of(music));
        }
        return ChatDto.builder()
                .id(chat.getId())
                .playlistId(chat.getPlaylist().getId())
                .content(chat.getContent())
                .sender(chat.getSender())
                .playlist(musics)
                .build();
    }
}
