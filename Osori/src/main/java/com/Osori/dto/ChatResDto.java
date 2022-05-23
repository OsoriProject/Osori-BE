package com.Osori.dto;

import com.Osori.domain.entity.Chat;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResDto {
    private List<ChatDto> chats = new ArrayList<>();

    public static ChatResDto of(List<Chat> chatList){
        List<ChatDto> chatResDto = new ArrayList<>();

        for(Chat chat : chatList){
            chatResDto.add(ChatDto.of(chat));
        }

        return ChatResDto.builder()
                .chats(chatResDto)
                .build();
    }
}
