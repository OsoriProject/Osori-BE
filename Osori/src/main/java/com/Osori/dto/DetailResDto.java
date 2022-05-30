package com.Osori.dto;

import com.Osori.domain.entity.Music;
import com.Osori.domain.entity.Playlist;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResDto {
    private Long id;
    private String name;
    private List<MusicDto> musics;

    public static DetailResDto of(Playlist playlist){
        List<MusicDto> musics = new ArrayList<>();
        for(Music music : playlist.getMusics()){
            musics.add(MusicDto.of(music));
        }

        return DetailResDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .musics(musics)
                .build();
    }
}
