package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo, HttpServletRequest request);
    List<PlaylistVo> getPlaylist(String uuid, int page, HttpServletRequest request);
    PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo);
    boolean deletePlaylist(Long playlistId, HttpServletRequest request);
    String addTrack(PlaylistTrackAddReqVo playlistTrackAddReqVo, HttpServletRequest request);
    PlaylistDetailVo getPlaylistTrack(Long playlistTrackId, HttpServletRequest request);
    boolean deleteTrack(Long playlistTrackId, HttpServletRequest request);

    //    PlaylistCountVo countPlaylist(Long userId);
    PlaylistEntity getPlaylistById(Long id);
    PlaylistCountVo countPlaylist(String uuid);
    PlaylistSearchVo playlistSearch(String keyword, int page, HttpServletRequest request);
}
