package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddReqVo;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServicIempl implements IArtistService{

    private final IArtistRepository iArtistRepository;

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
    public ArtistEntity getArtist(Long artistId) {
        Optional<ArtistEntity> artist = iArtistRepository.findById(artistId);

        if(artist.isPresent())
            return artist.get();
        else
            return null;
    }

    // 아티스트 정보수정
    @Override
    @Transactional
    public boolean updateArtist(Long artistId, String artistThumbUri) {

        ArtistEntity artistEntity = iArtistRepository.findById(artistId).orElseThrow();

        artistEntity.updateThumbnail(artistThumbUri);

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
