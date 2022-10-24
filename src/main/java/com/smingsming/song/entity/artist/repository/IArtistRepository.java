package com.smingsming.song.entity.artist.repository;

import com.smingsming.song.entity.artist.entity.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findAllByNameContains(Pageable pr, String keyword);
}
