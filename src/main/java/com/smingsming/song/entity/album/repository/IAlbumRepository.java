package com.smingsming.song.entity.album.repository;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.song.vo.SongVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAlbumRepository extends JpaRepository<AlbumEntity, Long> {


//    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
//            " s.id, s.songThumbUri, s.songName, artist.name, album.title, s.songUri, s.formal,s.userId " +
//            ")" +
//            " from SongEntity s" +
//            " left join AlbumEntity album on s.albumEntity.id = album.id " +
//            " left join ArtistEntity artist on s.artist.id = artist.id" +
//            " where s.songName like :keyword " +
//            " or album.title like :keyword " +
//            " or artist.name like :keyword "
//    )

    @Query(value = " select a from AlbumEntity a " +
            " where a.title like :keyword" +
            " or a.artist.name like :keyword ")
    List<AlbumEntity> getAlbumListByKeyword(Pageable pr, @Param("keyword") String keyword);

    List<AlbumEntity> findAllByArtist_Id(Pageable ap, Long artistId);
}
