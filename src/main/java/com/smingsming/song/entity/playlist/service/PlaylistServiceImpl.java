package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.global.utils.s3.FileInfoDto;
import com.smingsming.song.global.utils.s3.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements IPlaylistService {

    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IPlaylistRepository iPlaylistRepository;


    // 플레이리스트 생성
    @Override

    public PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo) {

        ModelMapper mapper = new ModelMapper();

        PlaylistEntity mapPlaylistEntity = mapper.map(playlistAddReqVo, PlaylistEntity.class);

        PlaylistEntity playlistEntity = iPlaylistRepository.save(mapPlaylistEntity);

        if (playlistEntity != null)
            return playlistEntity;
        else
            return null;

    }

    // 플레이리스트 조회
    @Override
    public List<PlaylistEntity> getPlaylist(Long userId) {
        List<PlaylistEntity> playlist = iPlaylistRepository.findAllByUserId(userId);

        if (!playlist.isEmpty()) {
            return playlist;
        }
        return null;
    }

    // 플레이리스트 정보 수정
    @Override
    public PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo) {

        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistUpdateReqVo.getId());

        if (playlist.isPresent()) {

            PlaylistUpdateReqVo updateReqVo = PlaylistUpdateReqVo.builder()
                    .id(playlist.get().getId())
                    .title(playlist.get().getTitle())
                    .playlistThumbnail(playlistUpdateReqVo.getPlaylistThumbnail())
                    .build();

        }

        return null;
    }

    // 플레이리스트 삭제
    @Override
    public boolean deletePlaylist(Long playlistId) {
        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistId);

        if (playlist.isPresent()) {
            iPlaylistRepository.deleteById(playlistId);
            return true;
        }

        return false;
    }

    // 플레이리스트 내 수록곡 추가
    @Override
    public Integer addTrack(PlaylistTrackEntity playlistTrackEntity) {

        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistTrackEntity.getPlaylistId());

        if (!playlist.isPresent()) {
            throw new IllegalStateException("플레이리스트가 존재하지 않습니다.");
        }


        return null;

    }
}
