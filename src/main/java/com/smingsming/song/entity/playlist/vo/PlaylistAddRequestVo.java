package com.smingsming.song.entity.playlist.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaylistAddRequestVo {

    private String title;
    private Long userId;

}
