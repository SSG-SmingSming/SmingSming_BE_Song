package com.smingsming.song.entity.artist.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArtistVo {
    private Long id;
    private String name;
    private String thumbnail;
    private LocalDate debutDate;

}
