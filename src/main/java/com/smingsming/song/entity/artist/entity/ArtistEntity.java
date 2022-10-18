package com.smingsming.song.entity.artist.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "artist")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String artistThumbnail;

    @NotNull
    private LocalDate debutDate;

    @Builder
    public ArtistEntity(String name, String artistThumbnail, LocalDate debutDate) {
        this.name = name;
        this.artistThumbnail = artistThumbnail;
        this.debutDate = debutDate;
    }

    public void updateThumbnail(String artistThumbnail) {
        this.artistThumbnail = artistThumbnail;
    }
}
