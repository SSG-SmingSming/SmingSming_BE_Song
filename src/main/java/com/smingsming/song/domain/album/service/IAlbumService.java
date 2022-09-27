package com.smingsming.song.domain.album.service;

import com.smingsming.song.domain.album.domain.Album;
import com.smingsming.song.domain.album.dto.RequestAddAlbumDto;
import org.springframework.web.multipart.MultipartFile;

public interface IAlbumService {
    Album addAlbum(RequestAddAlbumDto albumDto, MultipartFile albumThumbnail);
    boolean deleteAlbum(Long albumId);
    Album getAlbum(Long albumId);
}
