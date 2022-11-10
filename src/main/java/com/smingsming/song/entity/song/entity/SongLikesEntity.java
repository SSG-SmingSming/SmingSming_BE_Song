package com.smingsming.song.entity.song.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "songlikes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongLikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    @JsonIgnore
    private SongEntity songEntity;

    @Builder
    public SongLikesEntity(Long id, String uuid, SongEntity songEntity) {
        this.id = id;
        this.uuid = uuid;
        this.songEntity = songEntity;
    }
}
