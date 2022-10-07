package com.smingsming.song.entity.song.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalSongAddRequestVo {
    private Long album;
    private Long artist;
    private String songUri;
    private String songThumbUri;
    private String songName;
}
