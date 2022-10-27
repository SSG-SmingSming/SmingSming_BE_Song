package com.smingsming.song.entity.album.entity;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String albumThumbnail;

    @NotNull
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArtistEntity artist;

    public void updateAlbumInfo(String title, String albumThumbnail) {
        this.title = title;
        this.albumThumbnail = albumThumbnail;
    }
}
