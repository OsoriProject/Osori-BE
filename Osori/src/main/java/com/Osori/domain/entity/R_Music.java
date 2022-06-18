package com.Osori.domain.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value="music")
public class R_Music {
    @Id
    private String id;
    private String videoId;
    private String title;
    private String thumbnail;
}
