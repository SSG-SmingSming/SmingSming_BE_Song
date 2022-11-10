package com.smingsming.song.entity.playlist.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@Builder
public class PlaylistSearchVo {
    Long count;
    List<PlaylistVo> result;
}
