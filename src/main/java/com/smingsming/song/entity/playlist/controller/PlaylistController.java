package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.service.IPlaylistService;
import com.smingsming.song.entity.playlist.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final IPlaylistService iPlaylistService;

    // 플레이리스트 생성

    @PostMapping(value = "/add")
    public ResponseEntity<?> addPlaylist(@RequestBody PlaylistAddReqVo playlistAddReqVo, HttpServletRequest request) {
        PlaylistEntity playlistEntity = iPlaylistService.addPlaylist(playlistAddReqVo, request);

        if(playlistEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlistEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }

    // 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylist(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        List<PlaylistVo> result = iPlaylistService.getPlaylist(userId, request);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("플레이리스트가 존재하지 않습니다.");
    }

    // 플레이리스트 검색
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchPlaylist(@RequestParam(name = "keyword") String keyword, int page) {
        List<PlaylistVo> result = iPlaylistService.playlistSearch(keyword, page);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 플레이리스트 정보 수정
    @PutMapping(value = "/update")
    public ResponseEntity<?> editPlaylist(@RequestBody PlaylistUpdateReqVo playlistUpdateReqVo) {

        PlaylistEntity playlist = iPlaylistService.editPlaylist(playlistUpdateReqVo);

        if(playlist != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlist);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");

    }

    // 플레이리스트 삭제
    @DeleteMapping(value = "/delete/{playlistId}")
    public ResponseEntity<?> deletePlaylist(@PathVariable(value = "playlistId") Long playlistId, HttpServletRequest request) {
        boolean result = iPlaylistService.deletePlaylist(playlistId, request);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

    // 플레이리스트에 곡 추가, 앨범 내 모든 곡 추가 기능
    @PostMapping(value = "/add/track")
    public ResponseEntity<?> addTrackInPlaylist(@RequestBody PlaylistTrackAddReqVo playlistTrackAddReqVo, HttpServletRequest request) {

        String result = iPlaylistService.addTrack(playlistTrackAddReqVo, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 플레이리스트 내 수록곡 조회
    @GetMapping(value = "/getAll/track/{playlistId}")
    public ResponseEntity<?> getPlaylistTrack(@PathVariable(value = "playlistId") Long playlistId, HttpServletRequest request) {

        PlaylistDetailVo result = iPlaylistService.getPlaylistTrack(playlistId, request);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수록곡이 존재하지 않습니다.");

    }

    // 플레이리스트 내 수록곡 삭제
    @DeleteMapping(value = "/delete/track/{playlistTrackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable(value = "playlistTrackId") Long playlistTrackId) {
        boolean result = iPlaylistService.deleteTrack(playlistTrackId);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }
}
