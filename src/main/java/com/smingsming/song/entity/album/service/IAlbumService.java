package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.Album;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import org.springframework.web.multipart.MultipartFile;

public interface IAlbumService {
    Album addAlbum(AlbumAddRequestVo albumDto, MultipartFile albumThumbnail);
    boolean deleteAlbum(Long albumId);
    Album getAlbum(Long albumId);
}
