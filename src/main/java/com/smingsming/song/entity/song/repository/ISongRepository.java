package com.smingsming.song.entity.song.repository;

import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.vo.SongVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<SongEntity, Long> {

    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.albumEntity.albumThumbnail, s.songName, artist.name, album.title, s.songUri, s.formal,s.userId " +
            ")" +
            " from SongEntity s" +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id" +
            " where s.songName like :keyword " +
            " or album.title like :keyword " +
            " or artist.name like :keyword "
    )
    List<SongVo> getSongListByKeyword(Pageable pr, @Param("keyword") String keyword);


}
