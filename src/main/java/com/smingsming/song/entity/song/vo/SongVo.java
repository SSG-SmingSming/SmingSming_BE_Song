package com.smingsming.song.entity.song.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SongVo {
    private Long id;
    private String uuid;
    private Long albumId;
    private String artistName;
    private String songThumbnail;
    private String songName;
    private String songUri;
    private boolean isLike;
    private boolean isFormal;
}
