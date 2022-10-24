package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.service.IPlaylistLikesService;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesDeleteReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistLikesResVo;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("playlist/likes")
@RequiredArgsConstructor
public class PlaylistLikesController {

    private final IPlaylistLikesService iPlaylistLikesService;

    // 플레이리스트 좋아요 추가, 한 번 더 실행 시 취소
    @PostMapping(value = "/add/{playlistId}")
    public ResponseEntity<?> addPlaylistLikes(@PathVariable(value = "playlistId") Long playlistId,
                                              HttpServletRequest request) {

        String playlistLikes = iPlaylistLikesService.addPlaylistLikes(playlistId, request);

        return ResponseEntity.status(HttpStatus.OK).body(playlistLikes);
    }


    // 좋아요한 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylistLikes(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        List<PlaylistVo> result = iPlaylistLikesService.getPlaylistLikes(userId, request);

        if(result.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요한 플레이리스트가 존재하지 않습니다.");
    }

    // 플레이리스트 좋아요 취소
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deletePlaylistLikes(@RequestBody PlaylistLikesDeleteReqVo playlistLikesDeleteRequestVo) {
        boolean result = iPlaylistLikesService.deletePlaylistLikes(playlistLikesDeleteRequestVo);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }
}
