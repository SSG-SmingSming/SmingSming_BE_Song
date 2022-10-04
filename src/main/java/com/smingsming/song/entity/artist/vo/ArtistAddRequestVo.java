package com.smingsming.song.entity.artist.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ArtistAddRequestVo {
    private String name;
    private LocalDate debutDate;
}
