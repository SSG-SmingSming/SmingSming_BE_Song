package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlaylistTrackRepository extends JpaRepository<PlaylistTrackEntity, Long> {

    List<PlaylistTrackEntity> findAllByPlaylistId(Long playlistId);

    void deleteAllByPlaylistId(Long playlistId);
    void deleteAllBySongId(Long songId);
}
