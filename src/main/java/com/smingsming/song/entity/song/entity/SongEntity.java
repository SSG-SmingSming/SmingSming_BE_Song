package com.smingsming.song.entity.song.entity;


import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "song")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String songName;

    @Column(name = "is_formal")
    private boolean formal;

    private Long userId;

    private String songUri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private AlbumEntity albumEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArtistEntity artist;
}
