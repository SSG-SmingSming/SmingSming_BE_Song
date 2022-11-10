package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.*;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.SongVo;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements IAlbumService{

    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 앨범 등록
    @Override
    public AlbumEntity addAlbum(AlbumAddReqVo albumVo) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ArtistEntity artist = iArtistRepository.findById(albumVo.getArtistId()).orElseThrow();
        AlbumEntity mapAlbumEntity = mapper.map(albumVo, AlbumEntity.class);
        mapAlbumEntity.setArtist(artist);

        AlbumEntity albumEntity = iAlbumRepository.save(mapAlbumEntity);

        if(albumEntity != null)
            return albumEntity;
        else
            return null;
    }

    // 앨범 조회
    @Override
    public AlbumEntity getAlbum(Long albumId) {
        Optional<AlbumEntity> album = iAlbumRepository.findById(albumId);

        if(album.isPresent())
            return album.get();
        else
            return null;
    }

    // 앨범 검색
    @Override
    public AlbumSearchVo albumSearch(String keyword, int page) {
        Pageable pr = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        keyword = "%" + keyword.trim() + "%";

        Page<AlbumEntity> albumList = iAlbumRepository.getAlbumListByKeyword(pr, keyword);
        List<AlbumVo> returnVo = new ArrayList<>();

        albumList.forEach(v -> {
            returnVo.add(AlbumVo.builder()
                    .id(v.getId())
                    .name(v.getTitle())
                    .thumbnail(v.getAlbumThumbnail())
                    .artistId(v.getArtist().getId())
                    .artistName(v.getArtist().getName())
                    .build());
        });

        return AlbumSearchVo.builder()
                .count((int) albumList.getTotalElements())
                .result(returnVo).build();
    }

    // 앨범 상세 정보 조회
    @Override
    public AlbumDetailVo getAlbumDetail(Long albumId, HttpServletRequest request) {
        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        AlbumEntity album = iAlbumRepository.findById(albumId).orElseThrow();

        List<SongVo> songList = iSongRepository.findAllByAlbumEntityId(uuid, albumId);

        AlbumDetailVo returnVo = AlbumDetailVo.builder()
                .albumId(album.getId())
                .albumTitle(album.getTitle())
                .artistName(album.getArtist().getName())
                .thumbnail(album.getAlbumThumbnail())
                .songList(songList)
                .build();

        return returnVo;
    }

    // 앨범 삭제
    @Override
    public boolean deleteAlbum(Long albumId) {
        Optional<AlbumEntity> album = iAlbumRepository.findById(albumId);

        if(album.isPresent()) {
            iAlbumRepository.deleteById(albumId);
            return true;
        }
        else
            return false;
    }

    // 앨범 정보 수정
    @Override
    @Transactional
    public boolean updateAlbum(AlbumUpdateReqVo albumUpdateReqVo) {

        AlbumEntity albumEntity = iAlbumRepository.findById(albumUpdateReqVo.getAlbumId()).orElseThrow();
        albumEntity.updateAlbumInfo(albumUpdateReqVo.getTitle(), albumUpdateReqVo.getAlbumThumbnail());
        return true;
    }

}
