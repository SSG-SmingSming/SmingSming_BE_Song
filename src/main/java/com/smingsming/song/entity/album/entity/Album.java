package com.smingsming.song.entity.album.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String albumThumbnail;

    @NotNull
    private LocalDate releaseDate;

    @Builder
    public Album(String title, String albumThumbnail, LocalDate releaseDate) {
        this.title = title;
        this.albumThumbnail = albumThumbnail;
        this.releaseDate = releaseDate;
    }
}
