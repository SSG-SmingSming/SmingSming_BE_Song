package com.smingsming.song.entity.song.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongLikesAddReqVo {

    private Long songEntityId;
    private String uuid;
}
