package com.smingsming.song.entity.playlist.service;

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
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
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
public class PlaylistServiceImpl implements IPlaylistService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ISongRepository iSongRepository;
    private final IPlaylistRepository iPlaylistRepository;
    private final IPlaylistTrackRepository iPlaylistTrackRepository;


    // 플레이리스트 생성
    @Override

    public PlaylistEntity addPlaylist(PlaylistAddReqVo playlistAddReqVo, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        if(uuid == null) {
            return null;
        }

        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PlaylistEntity mapPlaylistEntity = mapper.map(playlistAddReqVo, PlaylistEntity.class);
        mapPlaylistEntity.setUuid(uuid);

        PlaylistEntity playlistEntity = iPlaylistRepository.save(mapPlaylistEntity);

        if (playlistEntity != null)
            return playlistEntity;
        else
            return null;
    }

    // 플레이리스트 조회
    @Override
    public List<PlaylistVo> getPlaylist(String searchedUser, int page, HttpServletRequest request) {
        String searchUser = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        Pageable pr = PageRequest.of(page - 1, 15, Sort.by("id").descending());

        List<PlaylistVo> playlist = iPlaylistRepository.getAllByUuid(searchUser, searchedUser, pr);

        if (!playlist.isEmpty()) {
            return playlist;
        }
        return null;
    }

    // 플레이리스트 정보 수정
    @Override
    @Transactional
    public PlaylistEntity editPlaylist(PlaylistUpdateReqVo playlistUpdateReqVo) {

        PlaylistEntity playlist = iPlaylistRepository.findById(playlistUpdateReqVo.getId()).orElseThrow();

        if (playlist != null) {

            if(playlistUpdateReqVo.getName() != null)
                playlist.updateName(playlistUpdateReqVo.getName());
            if(playlistUpdateReqVo.getThumbnail() != null)
                playlist.updateThumbnail(playlistUpdateReqVo.getThumbnail());

            return playlist;
        }

        return null;
    }

    // 플레이리스트 삭제
    @Override
    @Transactional
    public boolean deletePlaylist(Long playlistId, HttpServletRequest request) {
        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistId);


        if (playlist.isPresent()) {

            if(playlist.get().getUuid().equals(uuid)) {
                iPlaylistRepository.deleteById(playlistId);
                iPlaylistTrackRepository.deleteAllByPlaylistId(playlistId);
                return true;
            }
            return false;
        }
        return false;
    }

    // 플레이리스트 내 수록곡 추가
    @Override
    @Transactional
    public String addTrack(PlaylistTrackAddReqVo playlistTrackAddReqVo, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        SongEntity songEntity = iSongRepository.getById(playlistTrackAddReqVo.getSongId());
        PlaylistEntity playlistEntity = iPlaylistRepository.getById(playlistTrackAddReqVo.getPlaylistId());

//        if (uuid.equals(playlistEntity.getUuid())) {
        if (! uuid.equals(playlistEntity.getUuid())) {
            return "본인의 플레이리스트에만 추가할 수 있습니다.";
        }

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

    // 플레이리스트 검색
    @Override
    public PlaylistSearchVo playlistSearch(String keyword, int page, HttpServletRequest request) {

        Pageable pr = PageRequest.of(page - 1, 10, Sort.by("id").descending());

        keyword = "%" + keyword + "%";

        Page<PlaylistVo> result = iPlaylistRepository.findAllByTitleContains(keyword, pr);

        return PlaylistSearchVo.builder()
                .count(result.getTotalElements())
                .result(result.getContent()).build();
    }

    // 플레이리스트 갯수 집계
    @Override
    public PlaylistCountVo countPlaylist(String uuid) {

        Long playlistCount = iPlaylistRepository.countByUuid(uuid);

        PlaylistCountVo playlistCountVo = new PlaylistCountVo(playlistCount);

        return playlistCountVo;
    }

    // 플레이리스트 내 수록곡 조회
    @Override
    @Transactional
    public PlaylistDetailVo getPlaylistTrack(Long playlistId, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
//        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
//        PlaylistEntity playlist = iPlaylistRepository.findById(playlistId).orElseThrow();
        PlaylistVo playlist = iPlaylistRepository.getByIdWithLike(uuid, playlistId);

        List<PlaylistTrackVo> trackList = iPlaylistTrackRepository.findAllByPlaylistId(playlistId);

        return PlaylistDetailVo.builder()
                .playlistId(playlistId)
                .name(playlist.getPlaylistName())
                .thumbnail(playlist.getPlaylistThumbnail())
                .isLike(playlist.isPlaylistLike())
                .trackList(trackList)
                .userId(playlist.getUserId())
                .build();

//        return detailVo;
    }

    @Override
    public PlaylistEntity getPlaylistById(Long id) {
        return iPlaylistRepository.findById(id).orElseThrow();
    }

    // 플레이리스트 내 수록곡 삭제
    @Override
    public boolean deleteTrack(Long playlistTrackId, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        Optional<PlaylistTrackEntity> playlistTrack = iPlaylistTrackRepository.findById(playlistTrackId);

        if (playlistTrack.isPresent()) {
            Optional<PlaylistEntity> playlist = iPlaylistRepository.findById(playlistTrack.get().getPlaylistId());

            if (playlist.isPresent()) {
                if (playlist.get().getUuid().equals(uuid)) {
                    iPlaylistTrackRepository.deleteById(playlistTrackId);
                    return true;

                }
            }
        }
        return false;
    }
}
