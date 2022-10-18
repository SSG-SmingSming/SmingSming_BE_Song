package com.smingsming.song.entity.song.controller;

import com.smingsming.song.entity.song.service.ISongService;
import com.smingsming.song.entity.song.vo.CustomSongAddReqVo;
import com.smingsming.song.entity.song.vo.FormalSongAddReqVo;
import com.smingsming.song.entity.song.vo.SongVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add/formal")
    public ResponseEntity<?> formalSongAdd(@RequestBody FormalSongAddReqVo requestVo) {
        boolean result = iSongService.formalSongAdd(requestVo);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @PostMapping("/add/custom")
    public ResponseEntity<?> customSongAdd(@RequestBody CustomSongAddReqVo requestVo, HttpServletRequest request) {
        boolean result = iSongService.customSongAdd(requestVo, request);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @GetMapping("/play/{songId}")
    public ResponseEntity<?> songGet(@PathVariable(value = "songId")Long songId) {
        SongVo result = iSongService.songPlay(songId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/search")
    public ResponseEntity<?> songSearch(@RequestParam(defaultValue = "") String keyword,
                                        @RequestParam(name = "page", defaultValue = "1") int page) {
        List<SongVo> result = iSongService.songSearch(keyword, page);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @DeleteMapping("/delete/{songId}")
    public ResponseEntity<?> songDelete(@PathVariable(value = "songId")Long songId) {
        boolean result = iSongService.songDelete(songId);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @DeleteMapping("/delete/custom/{songId}")
    public ResponseEntity<?> customSongDelete(@PathVariable(value = "songId")Long songId, HttpServletRequest request) {
        boolean result = iSongService.customSongDelete(songId, request);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }
}
