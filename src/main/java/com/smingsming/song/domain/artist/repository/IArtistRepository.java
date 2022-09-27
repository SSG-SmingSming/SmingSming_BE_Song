package com.smingsming.song.domain.artist.repository;

import com.smingsming.song.domain.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArtistRepository extends JpaRepository<Artist, Long> {
}
