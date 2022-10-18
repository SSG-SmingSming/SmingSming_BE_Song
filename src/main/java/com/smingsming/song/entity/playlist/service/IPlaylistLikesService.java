package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.vo.PlaylistLikesAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResVo;

import java.util.List;

public interface IPlaylistLikesService {


    String addPlaylistLikes(PlaylistLikesAddReqVo playlistLikesAddReqVo);
    List<PlaylistLikesResVo> getPlaylistLikes(Long Id);
    boolean deletePlaylistLikes(PlaylistLikesDeleteReqVo playlistLikesDeleteReqVo);

}
