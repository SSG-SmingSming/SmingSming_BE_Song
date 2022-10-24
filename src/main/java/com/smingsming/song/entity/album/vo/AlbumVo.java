package com.smingsming.song.entity.album.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumVo {
    private Long id;
    private String name;
    private String thumbnail;
    private Long artistId;
    private String artistName;
}
