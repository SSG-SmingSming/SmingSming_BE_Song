package com.smingsming.song.entity.playlist.service;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.repository.IPlaylistLikesRepository;
import com.smingsming.song.entity.playlist.repository.IPlaylistRepository;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistLikesServiceImpl implements IPlaylistLikesService{

    private final IPlaylistLikesRepository iPlaylistLikesRepository;
    private final IPlaylistRepository iPlaylistRepository;
    private final JwtTokenProvider jwtTokenProvider;


    // 플레이리스트 좋아요 추가, 한 번 더 실행 시 취소
    @Override
    public String addPlaylistLikes(Long  playlistId, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        PlaylistEntity playlistEntity = iPlaylistRepository.findById(playlistId).orElseThrow();
        PlaylistLikesEntity playlistLikes = iPlaylistLikesRepository.findByUuidAndPlaylistEntityId(uuid, playlistEntity.getId());

        if (playlistLikes == null) {
            PlaylistLikesEntity addLikes = PlaylistLikesEntity.builder()
                    .uuid(uuid)
                    .playlistEntity(playlistEntity).build();

            iPlaylistLikesRepository.save(addLikes);
            return "좋아요 성공";
        }

        else {
            iPlaylistLikesRepository.delete(playlistLikes);
            return "좋아요 취소";
        }
    }


    // 좋아요한 플레이리스트 조회
    @Override
    public List<PlaylistVo> getPlaylistLikes(String uuid, HttpServletRequest request) {

        String searchUser = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        List<PlaylistVo> playlistLikes = iPlaylistLikesRepository.getAllByUuid(searchUser, uuid);

        return playlistLikes;
    }

    // 플레이리스트 좋아요 취소
    @Override
    public boolean deletePlaylistLikes(PlaylistLikesDeleteReqVo playlistLikesDeleteReqVo) {
        Optional<PlaylistLikesEntity> likes = iPlaylistLikesRepository.findById(playlistLikesDeleteReqVo.getId());

        if(likes.isPresent() && likes.get().getUuid().equals(playlistLikesDeleteReqVo.getUuid())) {
            iPlaylistLikesRepository.deleteById(playlistLikesDeleteReqVo.getId());
            return true;
        }

        return false;
    }
}
