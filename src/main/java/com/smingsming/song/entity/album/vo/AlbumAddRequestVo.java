package com.smingsming.song.entity.album.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AlbumAddRequestVo {
    private String title;
    private LocalDate releaseDate;
    private String albumThumbnail;
    private Long artistId;
}
