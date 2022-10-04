package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesAddRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResponseVo;

import java.util.List;

public interface IPlaylistLikesService {

    PlaylistLikesEntity addPlaylistLikes(PlaylistLikesAddRequestVo playlistLikesAddRequestVo);
    List<PlaylistLikesResponseVo> getPlaylistLikes(Long Id);
    boolean deletePlaylistLikes(PlaylistLikesDeleteRequestVo playlistLikesDeleteRequestVo);
}
