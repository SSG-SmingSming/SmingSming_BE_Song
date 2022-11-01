package com.smingsming.song.entity.song.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongLikesDeleteReqVo {

    private Long id;
    private String uuid;
}
