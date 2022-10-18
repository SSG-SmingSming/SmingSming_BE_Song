package com.smingsming.song.entity.artist.repository;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArtistRepository extends JpaRepository<ArtistEntity, Long> {
}
