package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.repository.IPlaylistTrackRepository;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements IPlaylistService {

    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IPlaylistRepository iPlaylistRepository;
    private final IPlaylistTrackRepository iPlaylistTrackRepository;


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
    @Transactional
    public String addTrack(PlaylistTrackEntity playlistTrackEntity) {

        SongEntity songEntity = iSongRepository.getById(playlistTrackEntity.getSongEntity().getId());
        PlaylistEntity playlist = iPlaylistRepository.getById(playlistTrackEntity.getPlaylistEntity().getId());

//        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistTrackEntity.getPlaylistEntity().getId());
//        Optional<AlbumEntity> albumEntity = iAlbumRepository.findById(playlistTrackEntity.getAlbumEntity().getId());

        if (playlist == null) {
            throw new IllegalStateException("플레이리스트가 존재하지 않습니다.");
        }

        if(songEntity == null) {
            throw new IllegalStateException("음원이 존재하지 않습니다.");
        }

        if(playlistTrackEntity == null) {
            PlaylistTrackEntity addTrack = PlaylistTrackEntity.builder()
                    .id(playlistTrackEntity.getId())
                    .songEntity(playlistTrackEntity.getSongEntity())
                    .playlistEntity(playlistTrackEntity.getPlaylistEntity()).build();

            iPlaylistTrackRepository.save(addTrack);
            return "플레이리스트에 선택된 곡이 담겼습니다.";

        }

        else {
            return "플레이리스트에 추가되지 않았습니다.";
        }
    }
}
