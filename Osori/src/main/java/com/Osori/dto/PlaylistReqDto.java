package com.Osori.dto;

import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistReqDto {
    private String name;
    private List<String> musics;
}
