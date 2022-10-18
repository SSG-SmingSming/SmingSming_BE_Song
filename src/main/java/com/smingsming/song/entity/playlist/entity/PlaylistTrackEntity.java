package com.smingsming.song.entity.playlist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tracklist")
@NoArgsConstructor
public class PlaylistTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long songId;
    private Long playlistId;
}
