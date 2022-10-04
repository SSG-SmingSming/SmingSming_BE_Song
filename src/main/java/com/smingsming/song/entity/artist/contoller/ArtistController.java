package com.smingsming.song.entity.artist.contoller;

import com.smingsming.song.entity.artist.entity.Artist;
import com.smingsming.song.entity.artist.vo.ArtistAddRequestVo;
import com.smingsming.song.entity.artist.service.IArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/song-server/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final IArtistService iArtistService;

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addArtist(@RequestParam("artistThumbnail") MultipartFile artistThumbnail,
                                       @RequestPart ArtistAddRequestVo artistDto) {
        Artist result = iArtistService.addArtist(artistDto, artistThumbnail);

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("추가 실패");
    }

    @GetMapping(value = "/get/{artistId}")
    public ResponseEntity<?> getArtist(@PathVariable("artistId") Long artistId) {
        Artist result = iArtistService.getArtist(artistId);

        if (result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 아티스트 번호입니다.");
    }

    @PutMapping(value = "/update/{artistId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateArtist(@PathVariable("artistId") Long artistId,
                                               @RequestParam("artistThumbnail") MultipartFile artistThumbnail) {
        boolean result = iArtistService.updateArtist(artistId, artistThumbnail);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body("수정 완료");
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("수정 실패");
    }

    @DeleteMapping(value = "/delete/{artistId}")
    public ResponseEntity<String> deleteArtist(@PathVariable("artistId") Long artistId) {
        boolean result = iArtistService.deleteArtist(artistId);

        if (result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("삭제 실패");
    }

}
