package com.smingsming.song.entity.playlist.vo;

import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistLikesResponseVo {

    private Long id;
    private Long playlistEntityId;

}
