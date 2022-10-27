package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddReqVo;
import com.smingsming.song.entity.album.vo.AlbumDetailVo;
import com.smingsming.song.entity.album.vo.AlbumUpdateReqVo;
import com.smingsming.song.entity.album.vo.AlbumVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAlbumService {
    AlbumEntity addAlbum(AlbumAddReqVo albumVo);
    List<AlbumVo> albumSearch(String keyword, int page);
    boolean updateAlbum(AlbumUpdateReqVo albumUpdateReqVo);
    boolean deleteAlbum(Long albumId);
    AlbumEntity getAlbum(Long albumId);
    AlbumDetailVo getAlbumDetail(Long albumId, HttpServletRequest request);
}
