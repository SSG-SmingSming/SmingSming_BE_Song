package com.smingsming.song.entity.song.repository;

import com.smingsming.song.entity.song.entity.SongLikesEntity;
import com.smingsming.song.entity.song.vo.SongVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongLikesRepository extends JpaRepository<SongLikesEntity, Long> {

    SongLikesEntity findByUserIdAndSongEntityId(Long userId, Long songId);
    List<SongLikesEntity> findAllByUserId(Long userId);

    @Query(" select new com.smingsming.song.entity.song.vo.SongVo( " +
            " s.id, s.songEntity.userId, s.songEntity.albumEntity.id, s.songEntity.artist.name, " +
            " s.songEntity.albumEntity.albumThumbnail, s.songEntity.songName, " +
            " s.songEntity.songUri," +
            " case when s2.id is not null then true else false end, " +
            " s.songEntity.formal " +
            " ) " +
            "from SongLikesEntity s " +
            " left join SongLikesEntity s2 on s.songEntity.id = s2.songEntity.id and s2.userId = :searchUser " +
            " where s.userId = :searchedUser ")
    List<SongVo> getAllByUserId(@Param("searchUser") Long searchUser, @Param("searchedUser") Long searchedUser);
}
