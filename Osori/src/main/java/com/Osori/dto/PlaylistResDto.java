package com.Osori.dto;

import com.Osori.domain.entity.Playlist;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistResDto {
    private List<PlaylistDto> playlists = new ArrayList<>();

    public static PlaylistResDto of(List<Playlist> playlists){
        List<PlaylistDto> playlistResDto = new ArrayList<>();

        for(Playlist playlist : playlists){
            playlistResDto.add(PlaylistDto.of(playlist));
        }

        return PlaylistResDto.builder()
                .playlists(playlistResDto)
                .build();
    }
}
