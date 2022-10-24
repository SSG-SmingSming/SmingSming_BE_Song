package com.smingsming.song.entity.song.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SongGetVo {
    private Long id;
    private Long albumId;
    private String artistName;
    private String thumbnail;
    private String name;
    private String songUri;
    private boolean isLike;
}
