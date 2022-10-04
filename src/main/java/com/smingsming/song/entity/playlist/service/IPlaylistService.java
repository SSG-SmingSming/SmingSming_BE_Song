package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistAddRequestVo;
import org.springframework.web.multipart.MultipartFile;

public interface IPlaylistService {

    PlaylistEntity addPlaylist(PlaylistAddRequestVo playlistAddRequestVo, MultipartFile playlistThumbnail);
    <List> PlaylistEntity getPlaylist(Long userId);
    PlaylistEntity editPlaylist(PlaylistEntity playlistEntity);
    boolean deletePlaylist(Long playlistId);

}
