package com.smingsming.song.entity.artist.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ArtistAddReqVo {
    private String name;
    private LocalDate debutDate;
}
