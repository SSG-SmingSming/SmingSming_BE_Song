package com.smingsming.song.domain.artist.service;

import com.smingsming.song.domain.artist.domain.Artist;
import com.smingsming.song.domain.artist.dto.RequestAddArtistDto;
import org.springframework.web.multipart.MultipartFile;

public interface IArtistService {
    Artist addArtist(RequestAddArtistDto artistDto, MultipartFile artistThumbnail);
    boolean updateArtist(Long artistId, MultipartFile artistThumbnail);
    boolean deleteArtist(Long artistId);

    Artist getArtist(Long artistId);
}
