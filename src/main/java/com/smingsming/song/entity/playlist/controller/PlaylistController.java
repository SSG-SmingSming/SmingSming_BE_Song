package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.entity.PlaylistTrackEntity;
import com.smingsming.song.entity.playlist.service.IPlaylistService;
import com.smingsming.song.entity.playlist.vo.PlaylistAddReqVo;
import com.smingsming.song.entity.playlist.vo.PlaylistUpdateReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/song-server/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final IPlaylistService iPlaylistService;

    // 플레이리스트 생성

    @PostMapping(value = "/add")
    public ResponseEntity<?> addPlaylist(@RequestBody PlaylistAddReqVo playlistAddReqVo) {
        PlaylistEntity playlistEntity = iPlaylistService.addPlaylist(playlistAddReqVo);

        if(playlistEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlistEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }

    // 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylist(@PathVariable(value = "userId") Long userId) {
        List<PlaylistEntity> result = iPlaylistService.getPlaylist(userId);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("플레이리스트가 존재하지 않습니다.");
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
    public ResponseEntity<?> deletePlaylist(@PathVariable(value = "playlistId") Long playlistId) {
        boolean result = iPlaylistService.deletePlaylist(playlistId);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

    // 플레이리스트에 곡 추가, 앨범 내 모든 곡 추가 기능
    @PostMapping(value = "/add/track/{id}")
    public ResponseEntity<?> addTrackInPlaylist(@RequestBody PlaylistTrackEntity playlistTrackEntity) {

        return null;
    }

}
