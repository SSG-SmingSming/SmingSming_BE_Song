package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistDetailVo;
import com.smingsming.song.entity.playlist.vo.PlaylistTrackAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo, HttpServletRequest request);
    List<PlaylistEntity> getPlaylist(Long userId);
    PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo);
    boolean deletePlaylist(Long playlistId);
    String addTrack(PlaylistTrackAddReqVo playlistTrackAddReqVo);
    PlaylistDetailVo getPlaylistTrack(Long playlistTrackId, HttpServletRequest request);
    boolean deleteTrack(Long playlistTrackId);
}
