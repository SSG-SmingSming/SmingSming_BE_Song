package com.smingsming.song.entity.song.controller;

import com.smingsming.song.entity.song.service.ISongService;
import com.smingsming.song.entity.song.vo.FormalSongAddRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {
    private final ISongService iSongService;

    @PostMapping("/formaladd")
    public ResponseEntity<?> formalSongAdd(@RequestBody FormalSongAddRequestVo requestVo) {
        boolean result = iSongService.formalSongAdd(requestVo);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(true);
        else
            return ResponseEntity.status(HttpStatus.OK).body(false);
    }
    
}
