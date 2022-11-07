package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddReqVo;
import com.smingsming.song.entity.artist.vo.ArtistSearchVo;
import com.smingsming.song.entity.artist.vo.ArtistVo;
import com.smingsming.song.entity.song.vo.SongVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IArtistService {
    ArtistEntity addArtist(ArtistAddReqVo artistVo);
    boolean updateArtist(Long artistId, ArtistVo artistVo);
    boolean deleteArtist(Long artistId);
    ArtistVo getArtist(Long artistId);
    ArtistSearchVo artistSearch(String keyWord, int page);
    List<AlbumVo> getAlbumByArtist(Long artistId, int page);
    List<SongVo> getSongByArtist(Long artistId, int page, HttpServletRequest request);
}
