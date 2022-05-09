package com.Osori.controller;

import com.Osori.dto.PlaylistResDto;
import com.Osori.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    public static final Long tmpUserId = 1L; // 로그인 상태 관리 어떻게 하는지 결정 후 적용

    @GetMapping
    public PlaylistResDto getPlaylist(){
        return playlistService.getPlaylist(tmpUserId);
    }
}
