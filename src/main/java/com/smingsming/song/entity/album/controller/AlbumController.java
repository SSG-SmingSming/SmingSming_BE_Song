package com.smingsming.song.entity.album.controller;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.vo.AlbumAddRequestVo;
import com.smingsming.song.entity.album.service.IAlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/song-server/album")
@RequiredArgsConstructor
public class AlbumController {
    private final IAlbumService iAlbumService;

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addAlbum(@RequestParam("albumThumbnail")MultipartFile albumThumbnail,
                                      @RequestPart AlbumAddRequestVo albumDto) {
        AlbumEntity result = iAlbumService.addAlbum(albumDto, albumThumbnail);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("추가 실패");
    }

    @GetMapping(value = "/get/{albumId}")
    public ResponseEntity<?> getAlbum(@PathVariable(value = "albumId") Long albumId) {
        AlbumEntity result = iAlbumService.getAlbum(albumId);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 앨범ID 입니다.");
    }

    @DeleteMapping(value = "/delete/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable(value = "albumId") Long albumId) {
        boolean result = iAlbumService.deleteAlbum(albumId);
        
        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

}
