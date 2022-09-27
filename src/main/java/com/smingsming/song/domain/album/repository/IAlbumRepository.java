package com.smingsming.song.domain.album.repository;

import com.smingsming.song.domain.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlbumRepository extends JpaRepository<Album, Long> {

}
