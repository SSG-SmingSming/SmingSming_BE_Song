package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import org.springframework.web.multipart.MultipartFile;

public interface IAlbumService {
    AlbumEntity addAlbum(AlbumAddRequestVo albumDto, MultipartFile albumThumbnail);
    boolean deleteAlbum(Long albumId);
    AlbumEntity getAlbum(Long albumId);
}
