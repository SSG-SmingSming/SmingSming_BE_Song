package com.smingsming.song.entity.artist.vo;

import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.song.vo.SongGetVo;

import java.time.LocalDate;
import java.util.List;

public class ArtistDetailVo {
    private Long id;
    private String name;
    private String thumbnail;
    private LocalDate debutDate;

    private List<AlbumVo> albumVoList;
    private List<SongGetVo> songList;
}
