package com.smingsming.song.domain.artist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestAddArtistDto {
    private String name;
    private LocalDate debutDate;
}
