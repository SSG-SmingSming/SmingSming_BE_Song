package com.smingsming.song.entity.album.service;

import com.smingsming.song.entity.album.entity.Album;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.global.utils.s3.FileInfoDto;
import com.smingsming.song.global.utils.s3.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements IAlbumService{

    private final IAlbumRepository iAlbumRepository;
    private final S3UploadService s3UploadService;

    @Override
    public Album addAlbum(AlbumAddRequestVo albumDto, MultipartFile albumThumbnail) {

        ModelMapper mapper = new ModelMapper();

        FileInfoDto fileInfoDto = FileInfoDto.multipartOf(albumThumbnail, "album");
        String uri = s3UploadService.store(fileInfoDto, albumThumbnail);

        Album mapAlbum = mapper.map(albumDto, Album.class);
        mapAlbum.setAlbumThumbnail(uri);

        Album album = iAlbumRepository.save(mapAlbum);

        if(album != null)
            return album;
        else
            return null;
    }

    @Override
    public boolean deleteAlbum(Long albumId) {
        Optional<Album> album = iAlbumRepository.findById(albumId);

        if(album.isPresent()) {
            iAlbumRepository.deleteById(albumId);
            return true;
        }
        else
            return false;
    }

    @Override
    public Album getAlbum(Long albumId) {
        Optional<Album> album = iAlbumRepository.findById(albumId);

        if(album.isPresent())
            return album.get();
        else
            return null;
    }
}
