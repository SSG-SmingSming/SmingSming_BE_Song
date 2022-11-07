package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistTrackVo;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistTrackRepository extends JpaRepository<PlaylistTrackEntity, Long> {


//    List<PlaylistTrackEntity> findAllByPlaylistId(Long playlistId);

    void deleteAllByPlaylistId(Long playlistId);
    void deleteAllBySongId(Long songId);

    @Query(value = "select new com.smingsming.song.entity.playlist.vo.PlaylistTrackVo( " +
            " t.id, t.songId, t.playlistId, s.songUri, s.albumEntity.albumThumbnail " +
            " ) " +
            " from PlaylistTrackEntity t, SongEntity s " +
            " where t.songId = s.id " +
            " and t.playlistId = :playlistId ")
    List<PlaylistTrackVo> findAllByPlaylistId(@Param("playlistId") Long playlistId);

}
