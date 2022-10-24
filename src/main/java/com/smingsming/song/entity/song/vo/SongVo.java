package com.smingsming.song.entity.song.vo;

import lombok.Data;


@Data
public class SongVo {
    private Long id;
    private String songThumbUri;
    private String songName;
    private String artistName;
    private String albumName;
    private String songUri;
    private boolean formal;
    private Long userId;

    public SongVo(Long id, String songThumbUri, String songName, String artistName, String albumName, String songUri, boolean formal, Long userId) {
        this.id = id;
        this.songThumbUri = songThumbUri;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.songUri = songUri;
        this.formal = formal;
        this.userId = userId;
    }
}
