package com.Osori.service;

import com.Osori.domain.entity.Music;
import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import com.Osori.domain.repository.MusicRepository;
import com.Osori.domain.repository.PlaylistRepository;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.DetailResDto;
import com.Osori.dto.MusicDto;
import com.Osori.dto.PlaylistReqDto;
import com.Osori.dto.PlaylistResDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import com.Osori.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;

    private User getUser(String userToken){
        String userToken_ = userToken.substring(7);
        String userEmail = tokenProvider.getUserEmailFromToken(userToken_);
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));
    }

    private Playlist checkAuth(String userToken, Long playlistId){
        User user = getUser(userToken);
        return playlistRepository.findByIdAndUser(playlistId, user)
                .orElseThrow(() -> new CustomException(ErrorCode.PERMISSION_NOT_ALLOWED));
    }

    public PlaylistResDto getPlaylist(String userToken){
        User user = getUser(userToken);
        List<Playlist> playlists = playlistRepository.findByUser(user);
        return PlaylistResDto.of(playlists);
    }

    public void addPlaylist(String userToken, PlaylistReqDto playlistReqDto){
        User user = getUser(userToken);
        Playlist playlist = playlistRepository.findById(playlistReqDto.getPlaylistId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_PLAYLIST));
        playlist.setName(playlistReqDto.getName());
        playlist.setUser(user);
        playlistRepository.save(playlist);
    }

    public void deletePlaylist(String userToken, Long playlistId){
        Playlist playlist = checkAuth(userToken, playlistId);
        playlistRepository.delete(playlist);
    }

    public DetailResDto detailPlaylist(String userToken, Long playlistId){
        Playlist playlist = checkAuth(userToken, playlistId);
        return DetailResDto.of(playlist);
    }
}
