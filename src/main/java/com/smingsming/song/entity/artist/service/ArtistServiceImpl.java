package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddReqVo;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.artist.vo.ArtistVo;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.SongVo;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
public class ArtistServiceImpl implements IArtistService{

    private final IArtistRepository iArtistRepository;
    private final IAlbumRepository iAlbumRepository;
    private final ISongRepository iSongRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 아티스트 등록
    @Override
    public ArtistEntity addArtist(ArtistAddReqVo artistVo) {

        ModelMapper mapper = new ModelMapper();

        ArtistEntity mapArtistEntity = mapper.map(artistVo, ArtistEntity.class);

        ArtistEntity artistEntity = iArtistRepository.save(mapArtistEntity);

        if(artistEntity != null)
            return artistEntity;
        else
            return null;
    }

    // 아티스트 조회
    @Override
    public ArtistVo getArtist(Long artistId) {
        ArtistEntity artist = iArtistRepository.findById(artistId).orElseThrow();

        ArtistVo returnVo = new ModelMapper().map(artist, ArtistVo.class);

        return returnVo;
    }

    // 아티스트별 앨범 조회
    @Override
    public List<AlbumVo> getAlbumByArtist(Long artistId, int page) {

        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());

        List<AlbumEntity> albumList = iAlbumRepository.findAllByArtist_Id(pr, artistId);

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

        return returnVo;
    }

    // 아티스트별 음원 조회
    @Override
    public List<SongVo> getSongByArtist(Long artistId, int page, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());

        List<SongVo> songList = iSongRepository.findAllByArtistId(uuid, artistId, pr);

        return songList;
    }

    // 아티스트 검색
    @Override
    public List<ArtistVo> artistSearch(String keyword, int page) {

        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());
        keyword = keyword.strip();
        List<ArtistEntity> artistList = iArtistRepository.findAllByNameContains(pr, keyword);

        ModelMapper mapper = new ModelMapper();
        List<ArtistVo> returnVo = new ArrayList<>();

        artistList.forEach(v -> {
            returnVo.add(mapper.map(v, ArtistVo.class));
        });


        return returnVo;
    }

    // 아티스트 정보 수정
    @Override
    @Transactional
    public boolean updateArtist(Long artistId, ArtistVo artistVo) {

        ArtistEntity artistEntity = iArtistRepository.findById(artistId).orElseThrow();

        artistEntity.updateThumbnail(artistVo.getArtistThumbnail());

        return true;
    }

    // 아티스트 삭제
    @Override
    public boolean deleteArtist(Long artistId) {

        Optional<ArtistEntity> artist = iArtistRepository.findById(artistId);

        if(artist.isPresent()) {
            iArtistRepository.deleteById(artistId);
            return true;
        }

        else
            return false;
    }
}
