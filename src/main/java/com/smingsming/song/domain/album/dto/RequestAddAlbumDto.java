package com.smingsming.song.domain.album.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestAddAlbumDto {
    private String title;
    private LocalDate releaseDate;
}
