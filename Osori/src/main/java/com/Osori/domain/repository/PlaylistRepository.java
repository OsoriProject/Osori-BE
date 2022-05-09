package com.Osori.domain.repository;

import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user);
}
