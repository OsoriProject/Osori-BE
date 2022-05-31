package com.Osori.dto;

import com.Osori.domain.entity.Music;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto {
    private Long id;
    private String videoId;
    private String title;
    private String thumbnail;

    public static MusicDto of(Music music){
        return MusicDto.builder()
                .id(music.getId())
                .videoId(music.getVid())
                .title(music.getTitle())
                .thumbnail(music.getThumbnail())
                .build();
    }
}
