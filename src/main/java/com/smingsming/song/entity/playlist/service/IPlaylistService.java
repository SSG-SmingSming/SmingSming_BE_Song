package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo, HttpServletRequest request);
    List<PlaylistVo> getPlaylist(Long userId, int page, HttpServletRequest request);
    PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo);
    boolean deletePlaylist(Long playlistId, HttpServletRequest request);
    String addTrack(PlaylistTrackAddReqVo playlistTrackAddReqVo, HttpServletRequest request);
    PlaylistDetailVo getPlaylistTrack(Long playlistTrackId, HttpServletRequest request);
    boolean deleteTrack(Long playlistTrackId, HttpServletRequest request);
    List<PlaylistVo> playlistSearch(String keyword, int page);
}
