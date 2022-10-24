package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.repository.IPlaylistTrackRepository;
import com.smingsming.song.entity.playlist.vo.*;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements IPlaylistService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;
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
    public String addTrack(PlaylistTrackAddReqVo playlistTrackAddReqVo) {

        SongEntity songEntity = iSongRepository.getById(playlistTrackAddReqVo.getSongId());
        PlaylistEntity playlistEntity = iPlaylistRepository.getById(playlistTrackAddReqVo.getPlaylistId());

        if (playlistEntity == null) {
            throw new IllegalStateException("플레이리스트가 존재하지 않습니다.");
        }

        if (songEntity == null) {
            throw new IllegalStateException("음원이 존재하지 않습니다.");
        }

        if (playlistTrackAddReqVo != null) {
            PlaylistTrackEntity addTrack = PlaylistTrackEntity.builder()
                    .songId(playlistTrackAddReqVo.getSongId())
                    .playlistId(playlistTrackAddReqVo.getPlaylistId()).build();

            iPlaylistTrackRepository.save(addTrack);
            return "플레이리스트에 선택된 곡이 담겼습니다.";
        } else {
            return "플레이리스트에 추가되지 않았습니다.";
        }
    }

    // 플레이리스트 내 수록곡 조회
    @Override
    @Transactional
    public PlaylistDetailVo getPlaylistTrack(Long playlistId, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        PlaylistEntity playlist = iPlaylistRepository.findById(playlistId).orElseThrow();


//        List<PlaylistTrackEntity> trackList = iPlaylistTrackRepository.findAllByPlaylistEntityId(playlistId);
        List<PlaylistTrackEntity> trackList = iPlaylistTrackRepository.findAllByPlaylistId(playlistId);

        List<PlaylistTrackVo> trackVoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        trackList.forEach(v -> {
            SongEntity song = iSongRepository.findById(v.getSongId()).get();

//            String test = iArtistRepository.findById(v.getSongEntity().getArtist().getId()).get().getName();
            trackVoList.add(PlaylistTrackVo.builder()
//                    .name(song.getSongName())
//                    .thumbnail(song.getAlbumEntity().getAlbumThumbnail())
//                    .artistName(song.getArtist().getName())
//                    .artistName(v.getSongEntity().getArtist().getName())
                    .id(v.getId())
                    .songId(v.getSongId())
                    .playlistId(v.getPlaylistId())
                    .build());
                });

        PlaylistDetailVo detailVo = PlaylistDetailVo.builder()
                .playlistId(playlistId)
                .name(playlist.getTitle())
                .thumbnail(playlist.getPlaylistThumbnail())
                .trackList(trackVoList)
                .build();

        return detailVo;
    }

    // 플레이리스트 내 수록곡 삭제
    @Override
    public boolean deleteTrack(Long playlistTrackId) {

        Optional<PlaylistTrackEntity> playlistTrack = iPlaylistTrackRepository.findById(playlistTrackId);

        if (playlistTrack.isPresent()) {
            iPlaylistTrackRepository.deleteById(playlistTrackId);
            return true;
        }
        return false;
    }
}
