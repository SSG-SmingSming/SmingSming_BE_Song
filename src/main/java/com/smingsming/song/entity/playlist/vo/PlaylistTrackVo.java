package com.smingsming.song.entity.playlist.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrackVo {

    private Long id;           // 플레이리스트 트랙 id
    private Long songId;
    private Long playlistId;
}
