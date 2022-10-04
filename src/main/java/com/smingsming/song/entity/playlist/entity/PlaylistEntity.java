package com.smingsming.song.entity.playlist.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "playlist")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private  Timestamp updateDate;

    private String playlistThumbnail;

    @NotNull
    private Long userId;


    // 음악 넣기 !

    @Builder
    public PlaylistEntity(String title, String playlistThumbnail, Long userId) {
        this.title = title;
        this.playlistThumbnail = playlistThumbnail;
        this.userId = userId;
    }

    public void updateThumbnail(String playlistThumbnail) {
        this.playlistThumbnail = playlistThumbnail;
    }
//
//    @OneToMany(
//            mappedBy = "playlistEntity",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY)
//    private List<PlaylistLikesEntity> playlistLikesEntityList;

}

