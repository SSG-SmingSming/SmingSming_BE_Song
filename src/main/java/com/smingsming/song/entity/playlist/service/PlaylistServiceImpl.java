package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.vo.PlaylistAddRequestVo;
import com.smingsming.song.global.utils.s3.FileInfoDto;
import com.smingsming.song.global.utils.s3.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements IPlaylistService{

    private final IPlaylistRepository iPlaylistRepository;
    private final S3UploadService s3UploadService;

    // 플레이리스트 생성
    @Override
    public PlaylistEntity addPlaylist(PlaylistAddRequestVo playlistAddRequestVo, MultipartFile playlistThumbnail) {

        ModelMapper mapper = new ModelMapper();

        FileInfoDto fileInfoDto = FileInfoDto.multipartOf(playlistThumbnail, "playlist");
        String uri = s3UploadService.store(fileInfoDto, playlistThumbnail);

        PlaylistEntity mapPlaylistEntity = mapper.map(playlistAddRequestVo, PlaylistEntity.class);
        mapPlaylistEntity.setPlaylistThumbnail(uri);

        PlaylistEntity playlistEntity = iPlaylistRepository.save(mapPlaylistEntity);

        if(playlistEntity != null)
            return playlistEntity;
        else
            return null;

    }

    // 플레이리스트 조회
    @Override
    public <List> PlaylistEntity getPlaylist(Long userId) {
        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(userId);

        if(playlist.isPresent()) {
            return playlist.get();
        }
        return null;
    }

    // 플레이리스트 편집
    @Override
    public PlaylistEntity editPlaylist(PlaylistEntity playlistEntity) {
        return null;
    }

    // 플레이리스트 삭제
    @Override
    public boolean deletePlaylist(Long playlistId) {
        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistId);

        if(playlist.isPresent()) {
            iPlaylistRepository.deleteById(playlistId);
            return true;
        }

        return false;
    }
}
