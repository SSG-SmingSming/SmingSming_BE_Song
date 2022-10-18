package com.smingsming.song.entity.album.repository;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlbumRepository extends JpaRepository<AlbumEntity, Long> {

}
