package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddReqVo;
import com.smingsming.song.entity.artist.vo.ArtistVo;
import com.smingsming.song.entity.song.vo.SongGetVo;
import com.smingsming.song.entity.song.vo.SongVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IArtistService {
    ArtistEntity addArtist(ArtistAddReqVo artistVo);
    boolean updateArtist(Long artistId, String artistThumbUri);
    boolean deleteArtist(Long artistId);
    ArtistEntity getArtist(Long artistId);
    List<ArtistVo> artistSearch(String keyWord, int page);
    List<AlbumVo> getAlbumByArtist(Long artistId, int page);
    List<SongGetVo> getSongByArtist(Long artistId, int page, HttpServletRequest request);
}
