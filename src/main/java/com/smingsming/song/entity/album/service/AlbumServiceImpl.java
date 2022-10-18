package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements IAlbumService{

    private final IAlbumRepository iAlbumRepository;

    @Override
    public AlbumEntity addAlbum(AlbumAddRequestVo albumVo) {

        ModelMapper mapper = new ModelMapper();
        AlbumEntity mapAlbumEntity = mapper.map(albumVo, AlbumEntity.class);

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
    public AlbumEntity getAlbum(Long albumId) {
        Optional<AlbumEntity> album = iAlbumRepository.findById(albumId);

        if(album.isPresent())
            return album.get();
        else
            return null;
    }
}
