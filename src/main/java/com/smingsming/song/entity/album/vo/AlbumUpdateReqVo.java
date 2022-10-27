package com.smingsming.song.entity.album.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumUpdateReqVo {

    private Long albumId;
    private String title;
    private String albumThumbnail;

}
