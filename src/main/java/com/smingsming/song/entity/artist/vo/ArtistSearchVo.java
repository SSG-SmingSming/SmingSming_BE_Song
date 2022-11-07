package com.smingsming.song.entity.artist.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ArtistSearchVo {
    int count;
    List<ArtistVo> result;
}
