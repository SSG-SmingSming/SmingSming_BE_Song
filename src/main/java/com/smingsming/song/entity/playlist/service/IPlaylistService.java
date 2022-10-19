package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo);
    List<PlaylistEntity> getPlaylist(Long userId);
    PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo);
    boolean deletePlaylist(Long playlistId);
    String addTrack(PlaylistTrackEntity playlistTrackEntity);

}
