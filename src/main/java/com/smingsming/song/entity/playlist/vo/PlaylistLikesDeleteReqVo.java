package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistLikesDeleteReqVo {

    private Long id;
    private String uuid;
    private Long playlistTrackId;
}
