package com.Osori.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResDto {
    Long id;
    String content;
    List<String> playlist;

    public static AnswerResDto of(String answer, List<String> playList, Long id){
        return AnswerResDto.builder()
                .id(id)
                .content(answer)
                .playlist(playList)
                .build();
    }
}
