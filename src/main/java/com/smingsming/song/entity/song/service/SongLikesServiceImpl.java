package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResVo;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.entity.SongLikesEntity;
import com.smingsming.song.entity.song.repository.ISongLikesRepository;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.SongLikesAddReqVo;
import com.smingsming.song.entity.song.vo.SongLikesDeleteReqVo;
import com.smingsming.song.entity.song.vo.SongLikesResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongLikesServiceImpl implements ISongLikesService{

    private final ISongRepository iSongRepository;
    private final ISongLikesRepository iSongLikesRepository;

    // 음원 좋아요 추가, 한 번 더 실행 시 취소
    @Override
    public String addSongLikes(SongLikesAddReqVo songLikesAddReqVo) {

        SongEntity song = iSongRepository.getById(songLikesAddReqVo.getSongEntityId());
        SongLikesEntity songLikes = iSongLikesRepository.findByUserIdAndSongEntityId(songLikesAddReqVo.getUserId(), songLikesAddReqVo.getSongEntityId());

        if(songLikes == null) {

            SongLikesEntity songLikesEntity = SongLikesEntity.builder()
                    .userId(songLikesAddReqVo.getUserId())
                    .songEntity(song).build();

            iSongLikesRepository.save(songLikesEntity);
            return "좋아요 성공";
        }

        else {
            iSongLikesRepository.delete(songLikes);
            return "좋아요 취소";
        }
    }

    // 좋아요한 음원 목록 조회
    @Override
    public List<SongLikesResVo> getSongLikes(Long userId) {
        Iterable<SongLikesEntity> songLikes = iSongLikesRepository.findAllByUserId(userId);

        List<SongLikesResVo> result = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();

        songLikes.forEach(v -> {
            result.add(mapper.map(v, SongLikesResVo.class));
        });

        return result;
    }

    // 음원 좋아요 취소
    @Override
    public boolean deleteSongLikes(SongLikesDeleteReqVo songLikesDeleteReqVo) {

        Optional<SongLikesEntity> likes = iSongLikesRepository.findById(songLikesDeleteReqVo.getId());

        if(likes.isPresent() && likes.get().getUserId().equals(songLikesDeleteReqVo.getUserId())) {
            iSongLikesRepository.deleteById(songLikesDeleteReqVo.getId());
            return true;
        }

        return false;
    }
}
