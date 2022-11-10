package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPlaylistLikesService {

    String addPlaylistLikes(Long playlistId , HttpServletRequest request);
    List<PlaylistVo> getPlaylistLikes(String uuid, HttpServletRequest request);
    boolean deletePlaylistLikes(PlaylistLikesDeleteReqVo playlistLikesDeleteReqVo);
}
