package com.smingsming.song.entity.song.repository;

import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.vo.SongVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<SongEntity, Long> {
    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.uuid, album.id, artist.name, album.albumThumbnail, " +
            " s.songName, s.songUri, " +
            " case when sl.id is not null then true else false end, " +
            " s.formal " +
            ")" +
            " from SongEntity s" +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id " +
            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.uuid = :uuid " +
            " where s.songName like :keyword " +
            " or album.title like :keyword " +
            " or artist.name like :keyword "
    )
    List<SongVo> getSongListByKeyword(Pageable pr, @Param("keyword") String keyword, @Param("uuid") String uuid);

    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.uuid, album.id, artist.name, album.albumThumbnail, " +
            " s.songName, s.songUri, " +
            " case when sl.id is not null then true else false end, " +
            " s.formal " +
            ")" +
            " from SongEntity s" +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id " +
            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.uuid = :uuid " +
            " where s.albumEntity.id = :albumId "
    )
    List<SongVo> findAllByAlbumEntityId(@Param("uuid") String uuid, @Param("albumId") Long albumId);

    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.uuid, album.id, artist.name, album.albumThumbnail, " +
            " s.songName, s.songUri, " +
            " case when sl.id is not null then true else false end, " +
            " s.formal " +
            ")" +
            " from SongEntity s" +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id " +
            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.uuid = :uuid " +
            " where s.artist.id = :artistId "
    )
    List<SongVo> findAllByArtistId(@Param("uuid") String uuid, @Param("artistId") Long artistId, Pageable pr);

    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.uuid, album.id, artist.name, album.albumThumbnail, " +
            " s.songName, s.songUri, " +
            " case when sl.id is not null then true else false end, " +
            " s.formal " +
            " ) " +
            " from SongEntity s " +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id " +
            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.uuid = :searchUser " +
            " where s.formal = false  " +
            " and s.uuid = :searchedUser "
    )
    List<SongVo> getAllByIsFormalAndSongId(@Param("searchUser") String searchUser, @Param("searchedUser") String searchedUser);

    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
            " s.id, s.uuid, album.id, artist.name, album.albumThumbnail, " +
            " s.songName, s.songUri, " +
            " case when sl.id is not null then true else false end, " +
            " s.formal " +
            ")" +
            " from SongEntity s" +
            " left join AlbumEntity album on s.albumEntity.id = album.id " +
            " left join ArtistEntity artist on s.artist.id = artist.id " +
            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.uuid = :userId " +
            " where s.songName like :keyword " +
            " or album.title like :keyword " +
            " or artist.name like :keyword "
    )
    Page<SongVo> test(Pageable pr, @Param("keyword") String keyword, @Param("userId") String userId);

//
//    @Query(value = "select new com.smingsming.song.entity.song.vo.SongVo(" +
//            " s.id, s.userId, album.id, artist.name, album.albumThumbnail, " +
//            " s.songName, s.songUri, " +
//            " case when sl.id is not null then true else false end, " +
//            " s.formal " +
//            ")" +
//            " from SongEntity s" +
//            " left join AlbumEntity album on s.albumEntity.id = album.id " +
//            " left join ArtistEntity artist on s.artist.id = artist.id " +
//            " left join SongLikesEntity sl on s.id = sl.songEntity.id and sl.userId = :userId " +
//            " where s.id = :songId "
//    )
//    SongVo getByUserId(@Param("userId") Long userId, @Param("songId") Long songId);
}
