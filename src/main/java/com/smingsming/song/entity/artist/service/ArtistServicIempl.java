package com.smingsming.song.entity.artist.service;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.vo.ArtistAddRequestVo;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.global.utils.s3.FileInfoDto;
import com.smingsming.song.global.utils.s3.S3UploadService;
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
    private final S3UploadService s3UploadService;

    @Override
    public ArtistEntity addArtist(ArtistAddRequestVo artistDto, MultipartFile artistThumbnail) {

        ModelMapper mapper = new ModelMapper();

        FileInfoDto fileInfoDto = FileInfoDto.multipartOf(artistThumbnail, "artist");
        String uri = s3UploadService.store(fileInfoDto, artistThumbnail);

        ArtistEntity mapArtistEntity = mapper.map(artistDto, ArtistEntity.class);
        mapArtistEntity.setArtistThumbnail(uri);

        ArtistEntity artistEntity = iArtistRepository.save(mapArtistEntity);

        if(artistEntity != null)
            return artistEntity;
        else
            return null;
    }

    @Override
    public ArtistEntity getArtist(Long artistId) {
        Optional<ArtistEntity> artist = iArtistRepository.findById(artistId);

        if(artist.isPresent())
            return artist.get();
        else
            return null;
    }

    @Override
    @Transactional
    public boolean updateArtist(Long artistId, MultipartFile artistThumbnail) {

        ArtistEntity artistEntity = iArtistRepository.findById(artistId).orElseThrow();

        FileInfoDto fileInfoDto = FileInfoDto.multipartOf(artistThumbnail, "artist");
        String uri = s3UploadService.store(fileInfoDto, artistThumbnail);

        artistEntity.updateThumbnail(uri);

        return true;
    }

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
