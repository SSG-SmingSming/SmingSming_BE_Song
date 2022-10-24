package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistVo {
    Long id;
    String name;
    String thumbnail;
    Long userId;
    boolean like;
}
