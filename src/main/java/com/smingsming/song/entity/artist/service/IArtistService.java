package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddRequestVo;
import org.springframework.web.multipart.MultipartFile;

public interface IArtistService {
    ArtistEntity addArtist(ArtistAddRequestVo artistDto, MultipartFile artistThumbnail);
    boolean updateArtist(Long artistId, MultipartFile artistThumbnail);
    boolean deleteArtist(Long artistId);

    ArtistEntity getArtist(Long artistId);
}
