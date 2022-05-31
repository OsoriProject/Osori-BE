package com.Osori.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeDto {
    private String videoId;
    private String title;
    private String thumbnail;
}
