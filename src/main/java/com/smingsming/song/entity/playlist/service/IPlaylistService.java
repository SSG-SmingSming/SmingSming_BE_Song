package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;
import com.smingsming.song.entity.song.entity.SongEntity;
import java.util.List;

public interface IPlaylistService {


    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo);
    List<PlaylistEntity> getPlaylist(Long userId);
    PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo);
    boolean deletePlaylist(Long playlistId);
    Integer addTrack(PlaylistTrackEntity playlistTrackEntity);

}
