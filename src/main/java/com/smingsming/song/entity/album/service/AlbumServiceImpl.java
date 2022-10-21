package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements IAlbumService{

    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;

    @Override
    public AlbumEntity addAlbum(AlbumAddRequestVo albumVo) {

        ModelMapper mapper = new ModelMapper();
        ArtistEntity artist = iArtistRepository.findById(albumVo.getArtistId()).orElseThrow();
        AlbumEntity mapAlbumEntity = mapper.map(albumVo, AlbumEntity.class);
        mapAlbumEntity.setArtist(artist);

        AlbumEntity albumEntity = iAlbumRepository.save(mapAlbumEntity);

        if(albumEntity != null)
            return albumEntity;
        else
            return null;
    }

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

    @Override
    public List<AlbumVo> albumSearch(String keyword, int page) {
        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());
        keyword = "%" + keyword.trim() + "%";

        List<AlbumEntity> albumList = iAlbumRepository.getAlbumListByKeyword(pr, keyword);
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

    @Override
    public AlbumEntity getAlbum(Long albumId) {
        Optional<AlbumEntity> album = iAlbumRepository.findById(albumId);

        if(album.isPresent())
            return album.get();
        else
            return null;
    }
}
