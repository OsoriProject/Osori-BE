package com.Osori.service;

import com.Osori.domain.entity.Music;
import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import com.Osori.domain.repository.MusicRepository;
import com.Osori.domain.repository.PlaylistRepository;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.PlaylistReqDto;
import com.Osori.dto.PlaylistResDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    private User getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));
        return user;
    }

    public PlaylistResDto getPlaylist(Long userId){
        User user = getUser(userId);
        List<Playlist> playlists = playlistRepository.findByUser(user);
        return PlaylistResDto.of(playlists);
    }

    public void addPlaylist(Long userId, PlaylistReqDto playlistReqDto){
        User user = getUser(userId);
        Playlist playlist = playlistRepository.save(Playlist.builder()
                .name(playlistReqDto.getName())
                .user(user).build());

        List<Music> musicList = new ArrayList<>();
        for(String music:playlistReqDto.getMusics()){
            Music m = Music.builder()
                    .url(music)
                    .playlist(playlist)
                    .build();
            musicList.add(m);
        }
        musicRepository.saveAll(musicList);
    }
}
