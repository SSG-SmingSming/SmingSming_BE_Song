package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.service.IPlaylistLikesService;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesAddRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteRequestVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song-server/playlist/likes")
@RequiredArgsConstructor
public class PlaylistLikesController {

    private final IPlaylistLikesService iPlaylistLikesService;

    // 플레이리스트 좋아요 추가
    @PostMapping(value = "/add")
    public ResponseEntity<?> addPlaylistLikes(@RequestBody PlaylistLikesAddRequestVo playlistLikesAddRequestVo) {
        PlaylistLikesEntity playlistLikesEntity  = iPlaylistLikesService.addPlaylistLikes(playlistLikesAddRequestVo);

        if(playlistLikesEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlistLikesEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }


    // 좋아요한 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylistLikes(@PathVariable(value = "userId") Long userId) {
        List<PlaylistLikesResponseVo> result = iPlaylistLikesService.getPlaylistLikes(userId);

        if(result.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요한 플레이리스트가 존재하지 않습니다.");
    }

    // 플레이리스트 좋아요 취소
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deletePlaylistLikes(@RequestBody PlaylistLikesDeleteRequestVo playlistLikesDeleteRequestVo) {
        boolean result = iPlaylistLikesService.deletePlaylistLikes(playlistLikesDeleteRequestVo);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }
}
