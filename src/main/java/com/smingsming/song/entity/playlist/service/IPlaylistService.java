package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo, MultipartFile playlistThumbnail);
    List<PlaylistEntity> getPlaylist(Long userId);
    PlaylistEntity editPlaylist(PlaylistEntity playlistEntity);
    boolean deletePlaylist(Long playlistId);
    Integer addTrack(PlaylistTrackEntity playlistTrackEntity);

}
