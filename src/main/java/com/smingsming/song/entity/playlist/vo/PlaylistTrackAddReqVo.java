package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PlaylistTrackAddReqVo {

    private Long songId;
    private Long playlistId;
}
