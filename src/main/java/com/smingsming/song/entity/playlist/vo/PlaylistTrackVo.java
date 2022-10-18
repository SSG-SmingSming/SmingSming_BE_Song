package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PlaylistTrackVo {

    private Long id;
    private Long songId;
}
