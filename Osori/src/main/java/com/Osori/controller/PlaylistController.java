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
@RequestMapping("/playlist/{userId}")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<PlaylistResDto> getPlaylist(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(playlistService.getPlaylist(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPlaylist(@PathVariable("userId") Long userId, @RequestBody PlaylistReqDto playlistReqDto){
        playlistService.addPlaylist(userId, playlistReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable("userId") Long userId, @PathVariable("id") Long playlistId){
        playlistService.deletePlaylist(userId, playlistId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailResDto> detailPlaylist(@PathVariable("userId") Long userId, @PathVariable("id") Long playlistId){
        return new ResponseEntity<>(playlistService.detailPlaylist(userId, playlistId), HttpStatus.OK);
    }
}
