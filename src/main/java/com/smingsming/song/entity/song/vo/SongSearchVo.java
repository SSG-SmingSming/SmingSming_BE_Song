package com.smingsming.song.entity.song.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SongSearchVo {
    int count;
    List<SongVo> result;
}
