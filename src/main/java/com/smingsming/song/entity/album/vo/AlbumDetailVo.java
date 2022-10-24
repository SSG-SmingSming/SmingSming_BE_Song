package com.smingsming.song.entity.album.vo;

import com.smingsming.song.entity.song.vo.SongGetVo;
import com.smingsming.song.entity.song.vo.SongVo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlbumDetailVo {
    Long albumId;
    String albumTitle;
    String thumbnail;
    String artistName;

    List<SongGetVo> songList;
}
