package com.smingsming.song.entity.playlist.controller;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.service.IPlaylistService;
import com.smingsming.song.entity.playlist.vo.PlaylistAddRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/song-server/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final IPlaylistService iPlaylistService;

    // 플레이리스트 생성
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addArtist(@RequestParam("playlistThumbnail") MultipartFile playlistThumbnail,
                                       @RequestPart PlaylistAddRequestVo playlistAddRequestVo) {
        PlaylistEntity playlistEntity = iPlaylistService.addPlaylist(playlistAddRequestVo, playlistThumbnail);

        if(playlistEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(playlistEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }


    // 플레이리스트 조회
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<?> getPlaylist(@PathVariable(value = "userId") Long userId) {
        PlaylistEntity result = iPlaylistService.getPlaylist(userId);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("플레이리스트가 존재하지 않습니다.");
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


}
