package com.smingsming.song.entity.playlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "playlistlikes")
@NoArgsConstructor
public class PlaylistLikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlistEntity_id")
    @JsonIgnore
    private PlaylistEntity playlistEntity;


    @Builder
    public PlaylistLikesEntity(Long id, String uuid, PlaylistEntity playlistEntity) {
        this.id = id;
        this.uuid = uuid;
        this.playlistEntity = playlistEntity;
    }
}
