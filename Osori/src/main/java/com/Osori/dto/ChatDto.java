package com.Osori.dto;

import com.Osori.domain.entity.Chat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long id;
    private String content;
    private String sender;

    public static ChatDto of(Chat chat){
        return ChatDto.builder()
                .id(chat.getId())
                .content(chat.getContent())
                .sender(chat.getSender())
                .build();
    }
}
