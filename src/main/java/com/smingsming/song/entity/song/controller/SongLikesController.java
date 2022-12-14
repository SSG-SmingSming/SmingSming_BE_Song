package com.smingsming.song.entity.song.controller;

import com.smingsming.song.entity.song.entity.SongLikesEntity;
import com.smingsming.song.entity.song.repository.ISongLikesRepository;
import com.smingsming.song.entity.song.service.ISongLikesService;
import com.smingsming.song.entity.song.vo.SongLikesAddReqVo;
import com.smingsming.song.entity.song.vo.SongLikesDeleteReqVo;
import com.smingsming.song.entity.song.vo.SongLikesResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/song/likes")
@RequiredArgsConstructor
public class SongLikesController {

    private final ISongLikesService iSongLikesService;
    private final ISongLikesRepository iSongLikesRepository;

    // 음원 좋아요 추가, 한 번 더 실행 시 취소
    @PostMapping("/add/{songId}")
    public ResponseEntity<?> addSongLikes(@PathVariable(value = "songId") Long songId,
                                          HttpServletRequest request) {

        String songLikes = iSongLikesService.addSongLikes(songId, request);

        return ResponseEntity.status(HttpStatus.OK).body(songLikes);

    }

    // 좋아요한 음원 목록 조회
    @GetMapping(value = "/get/{uuid}")
    public ResponseEntity<?> getPlaylistLikes(@PathVariable(value = "uuid") String uuid) {
        List<SongLikesResVo> result = iSongLikesService.getSongLikes(uuid);

        return ResponseEntity.status(HttpStatus.OK).body(result);
//        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    // 음원 좋아요 취소
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deletePlaylistLikes(@RequestBody SongLikesDeleteReqVo songLikesDeleteReqVo) {
        boolean result = iSongLikesService.deleteSongLikes(songLikesDeleteReqVo);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("취소 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("취소 실패");
    }
}
