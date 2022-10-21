package com.smingsming.song.entity.song.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalSongAddReqVo {
    private Long album;
    private Long artist;
    private String songUri;
    private String songName;
}
