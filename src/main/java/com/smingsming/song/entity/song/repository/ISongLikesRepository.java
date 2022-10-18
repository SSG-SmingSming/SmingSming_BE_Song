package com.smingsming.song.entity.song.repository;

import com.smingsming.song.entity.song.entity.SongLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISongLikesRepository extends JpaRepository<SongLikesEntity, Long> {

    SongLikesEntity findByUserIdAndSongEntityId(Long userId, Long songId);
    List<SongLikesEntity> findAllByUserId(Long userId);
}
