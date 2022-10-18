package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    List<PlaylistEntity> findAllByUserId(Long userId);
}
