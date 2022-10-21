package com.smingsming.song.entity.album.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumVo {
    private Long id;
    private String name;
    private String thumbnail;
    private Long artistId;
    private String artistName;
}
