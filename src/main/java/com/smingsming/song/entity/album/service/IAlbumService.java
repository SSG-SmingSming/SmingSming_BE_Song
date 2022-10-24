package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import com.smingsming.song.entity.album.vo.AlbumDetailVo;
import com.smingsming.song.entity.album.vo.AlbumVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAlbumService {
    AlbumEntity addAlbum(AlbumAddRequestVo albumVo);
    List<AlbumVo> albumSearch(String keyword, int page);
    boolean deleteAlbum(Long albumId);
    AlbumEntity getAlbum(Long albumId);
    AlbumDetailVo getAlbumDetail(Long albumId, HttpServletRequest request);
}
