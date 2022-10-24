package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistTrackVo;
import com.smingsming.song.entity.song.vo.SongGetVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPlaylistTrackRepository extends JpaRepository<PlaylistTrackEntity, Long> {

//    List<PlaylistTrackEntity> findAllByPlaylistEntityId(Long playlistId);
    List<PlaylistTrackEntity> findAllByPlaylistId(Long playlistId);
}
