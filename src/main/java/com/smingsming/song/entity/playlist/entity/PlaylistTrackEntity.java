package com.smingsming.song.entity.playlist.entity;

import com.smingsming.song.entity.song.entity.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "tracklist")
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SongEntity songEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlaylistEntity playlistEntity;
}
