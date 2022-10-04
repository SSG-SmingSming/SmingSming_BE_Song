package com.smingsming.song.entity.artist.repository;

import com.smingsming.song.entity.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArtistRepository extends JpaRepository<Artist, Long> {
}
