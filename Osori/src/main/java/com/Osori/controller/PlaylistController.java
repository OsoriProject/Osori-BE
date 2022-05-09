package com.Osori.controller;

import com.Osori.domain.entity.Playlist;
import com.Osori.dto.DetailResDto;
import com.Osori.dto.PlaylistReqDto;
import com.Osori.dto.PlaylistResDto;
import com.Osori.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    public static final Long tmpUserId = 1L; // 로그인 상태 관리 어떻게 하는지 결정 후 적용

    @GetMapping
    public ResponseEntity<PlaylistResDto> getPlaylist(){
        return new ResponseEntity<>(playlistService.getPlaylist(tmpUserId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPlaylist(@RequestBody PlaylistReqDto playlistReqDto){
        playlistService.addPlaylist(tmpUserId, playlistReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable("id") Long playlistId){
        playlistService.deletePlaylist(playlistId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailResDto> detailPlaylist(@PathVariable("id") Long playlistId){
        return new ResponseEntity<>(playlistService.detailPlaylist(playlistId), HttpStatus.OK);
    }
}
