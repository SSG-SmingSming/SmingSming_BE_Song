package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddReqVo;
import org.springframework.web.multipart.MultipartFile;

public interface IArtistService {
    ArtistEntity addArtist(ArtistAddReqVo artistVo);
    boolean updateArtist(Long artistId, String artistThumbUri);
    boolean deleteArtist(Long artistId);
    ArtistEntity getArtist(Long artistId);
}
