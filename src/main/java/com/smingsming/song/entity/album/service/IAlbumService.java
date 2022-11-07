package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAlbumService {
    AlbumEntity addAlbum(AlbumAddReqVo albumVo);
    AlbumSearchVo albumSearch(String keyword, int page);
    boolean updateAlbum(AlbumUpdateReqVo albumUpdateReqVo);
    boolean deleteAlbum(Long albumId);
    AlbumEntity getAlbum(Long albumId);
    AlbumDetailVo getAlbumDetail(Long albumId, HttpServletRequest request);
}
