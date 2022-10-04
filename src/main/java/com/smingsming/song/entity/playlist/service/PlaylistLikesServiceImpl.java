package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistLikesRepository;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesAddRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResponseVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistLikesServiceImpl implements IPlaylistLikesService{

    private final IPlaylistLikesRepository iPlaylistLikesRepository;
    private final IPlaylistRepository iPlaylistRepository;

    // 플레이리스트 좋아요 추가
    @Override
    public PlaylistLikesEntity addPlaylistLikes(PlaylistLikesAddRequestVo playlistLikesAddRequestVo) {

        Optional<PlaylistEntity> playlistEntity = iPlaylistRepository.findById(playlistLikesAddRequestVo.getPlaylistEntityId());

        if (playlistEntity.isPresent()) {

            PlaylistLikesEntity playlistLikesEntity = PlaylistLikesEntity.builder()
                    .userId(playlistLikesAddRequestVo.getUserId())
                    .playlistEntity(playlistEntity.get()).build();

            PlaylistLikesEntity result = iPlaylistLikesRepository.save(playlistLikesEntity);
            return result;
        }

        else
            return null;
    }


    // 좋아요한 플레이리스트 조회
    @Override
    public List<PlaylistLikesResponseVo> getPlaylistLikes(Long userId) {
        Iterable<PlaylistLikesEntity> playlistLikes = iPlaylistLikesRepository.findAllByUserId(userId);

        List<PlaylistLikesResponseVo> result = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();

        playlistLikes.forEach(v -> {
            result.add(mapper.map(v, PlaylistLikesResponseVo.class));
        });

        return result;

    }

    // 플레이리스트 좋아요 취소
    @Override
    public boolean deletePlaylistLikes(PlaylistLikesDeleteRequestVo playlistLikesDeleteRequestVo) {
//        Optional<PlaylistLikesEntity> user = iPlaylistLikesRepository.findById(playlistLikesDeleteRequestVo.getUserId());
        Optional<PlaylistLikesEntity> likes = iPlaylistLikesRepository.findById(playlistLikesDeleteRequestVo.getId());

        if(likes.isPresent() && likes.get().getUserId().equals(playlistLikesDeleteRequestVo.getUserId())) {
            iPlaylistLikesRepository.deleteById(playlistLikesDeleteRequestVo.getId());
            return true;
        }

        return false;
    }
}
