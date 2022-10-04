package com.smingsming.song.entity.playlist.repository;


import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPlaylistLikesRepository extends JpaRepository<PlaylistLikesEntity, Long> {

    Optional<PlaylistLikesEntity> findByUserIdAndPlaylistEntityId(Long userId, Long playlistId);
    List<PlaylistLikesEntity> findAllByUserId(Long userId);
}
