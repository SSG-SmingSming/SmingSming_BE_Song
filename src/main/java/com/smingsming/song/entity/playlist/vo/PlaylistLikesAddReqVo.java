package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistLikesAddReqVo {

    private Long playlistEntityId;
    private Long userId;
}
