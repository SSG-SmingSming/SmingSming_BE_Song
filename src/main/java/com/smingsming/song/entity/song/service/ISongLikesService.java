package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.song.entity.SongLikesEntity;
import com.smingsming.song.entity.song.vo.SongLikesAddReqVo;
import com.smingsming.song.entity.song.vo.SongLikesDeleteReqVo;
import com.smingsming.song.entity.song.vo.SongLikesResVo;

import java.util.List;

public interface ISongLikesService {

    String addSongLikes(SongLikesAddReqVo songLikesAddReqVo);
    List<SongLikesResVo> getSongLikes(Long Id);
    boolean deleteSongLikes(SongLikesDeleteReqVo songLikesDeleteReqVo);
}