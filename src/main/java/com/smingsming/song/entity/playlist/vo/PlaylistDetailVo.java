package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlaylistDetailVo {

    private Long playlistId;
    private String name;
    private String thumbnail;
    private boolean isLike;
    private String userId;

    List<PlaylistTrackVo> trackList;

}
