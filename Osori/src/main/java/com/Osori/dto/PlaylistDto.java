package com.Osori.dto;

import com.Osori.domain.entity.Playlist;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistDto {
    private Long id;
    private String name;
    private String thumbnail;

    public static PlaylistDto of(Playlist playlist){
        return PlaylistDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .thumbnail(playlist.getThumbnail())
                .build();
    }
}
