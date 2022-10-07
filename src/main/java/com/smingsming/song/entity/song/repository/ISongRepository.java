package com.smingsming.song.entity.song.repository;

import com.smingsming.song.entity.song.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<SongEntity, Long> {
}
