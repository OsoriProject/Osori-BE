package com.Osori.dto;

import com.Osori.domain.entity.User;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {
    private Long id;
    private String nickname;

    public static UserResDto of(User user){
        return UserResDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
