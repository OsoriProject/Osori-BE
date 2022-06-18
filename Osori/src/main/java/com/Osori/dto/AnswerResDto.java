package com.Osori.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResDto {
    Long id;
    String content;
    Long playlistId;
    List<YoutubeDto> playlist;

    public static AnswerResDto of(String answer, List<YoutubeDto> playList, Long id, Long playlistId){
        return AnswerResDto.builder()
                .id(id)
                .content(answer)
                .playlistId(playlistId)
                .playlist(playList)
                .build();
    }
}
