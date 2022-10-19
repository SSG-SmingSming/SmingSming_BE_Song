package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistTrackRepository extends JpaRepository<PlaylistTrackEntity, Long> {
}
