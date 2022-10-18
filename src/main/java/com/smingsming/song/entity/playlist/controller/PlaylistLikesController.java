package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.service.IPlaylistLikesService;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResVo;
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
    public ResponseEntity<?> addPlaylistLikes(@RequestBody PlaylistLikesAddReqVo playlistLikesAddReqVo) {
        PlaylistLikesEntity playlistLikesEntity  = iPlaylistLikesService.addPlaylistLikes(playlistLikesAddReqVo);

        if(playlistLikesEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlistLikesEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }


    // 좋아요한 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylistLikes(@PathVariable(value = "userId") Long userId) {
        List<PlaylistLikesResVo> result = iPlaylistLikesService.getPlaylistLikes(userId);

        if(result.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요한 플레이리스트가 존재하지 않습니다.");
    }

    // 플레이리스트 좋아요 취소
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deletePlaylistLikes(@RequestBody PlaylistLikesDeleteReqVo playlistLikesDeleteReqVo) {
        boolean result = iPlaylistLikesService.deletePlaylistLikes(playlistLikesDeleteReqVo);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }
}
