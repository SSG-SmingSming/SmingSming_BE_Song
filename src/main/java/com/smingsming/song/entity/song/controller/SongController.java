package com.smingsming.song.entity.song.controller;

import com.smingsming.song.entity.song.service.ISongService;
import com.smingsming.song.entity.song.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {
    private final ISongService iSongService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Catalog Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    // 정식음원 추가
    @PostMapping("/add/formal")
    public ResponseEntity<?> formalSongAdd(@RequestBody FormalSongAddReqVo requestVo) {
        boolean result = iSongService.formalSongAdd(requestVo);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    // 미정식음원 추가
    @PostMapping("/add/custom")
    public ResponseEntity<?> customSongAdd(@RequestBody CustomSongAddReqVo requestVo, HttpServletRequest request) {
        boolean result = iSongService.customSongAdd(requestVo, request);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    // 재생정보 조회
    @GetMapping("/play/{songId}")
    public ResponseEntity<?> songGet(@PathVariable Long songId, HttpServletRequest request) {
        SongVo result = iSongService.songPlay(songId, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 음악 검색
    @GetMapping("/search")
    public ResponseEntity<?> songSearch(@RequestParam(defaultValue = "") String keyword,
                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                        HttpServletRequest request) {
        SongSearchVo result = iSongService.songSearch(keyword, page, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 사용자 업로드 음원 목록 조회
    @GetMapping("/get/custom/{uuid}")
    public ResponseEntity<?> customSongGet(@PathVariable(name = "uuid") String uuid,
                                           HttpServletRequest request) {
        List<SongVo> result = iSongService.customSongGet(uuid, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 전체 검색
    @GetMapping("/total/search")
    public ResponseEntity<?> totalSearch(@RequestParam(defaultValue = "") String keyword,
                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                        HttpServletRequest request) {
        SearchResultVo result = iSongService.totalSearch(keyword, page, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 정식음원 삭제
    @DeleteMapping("/delete/{songId}")
    public ResponseEntity<?> songDelete(@PathVariable(value = "songId")Long songId) {
        boolean result = iSongService.songDelete(songId);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    // 비정식음원 삭제
    @DeleteMapping("/delete/custom/{songId}")
    public ResponseEntity<?> customSongDelete(@PathVariable(value = "songId")Long songId, HttpServletRequest request) {
        boolean result = iSongService.customSongDelete(songId, request);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }
}
