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

    @GetMapping
    public ResponseEntity<PlaylistResDto> getPlaylist(@RequestHeader(value = "Authorization") String userToken){
        return new ResponseEntity<>(playlistService.getPlaylist(userToken), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPlaylist(@RequestHeader(value = "Authorization") String userToken, @RequestBody PlaylistReqDto playlistReqDto){
        playlistService.addPlaylist(userToken, playlistReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@RequestHeader(value = "Authorization") String userToken, @PathVariable("id") Long playlistId){
        playlistService.deletePlaylist(userToken, playlistId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailResDto> detailPlaylist(@RequestHeader(value = "Authorization") String userToken, @PathVariable("id") Long playlistId){
        return new ResponseEntity<>(playlistService.detailPlaylist(userToken, playlistId), HttpStatus.OK);
    }
}
