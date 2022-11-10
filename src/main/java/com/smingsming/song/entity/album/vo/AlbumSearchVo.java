package com.smingsming.song.entity.album.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlbumSearchVo {
    int count;
    List<AlbumVo> result;
}
