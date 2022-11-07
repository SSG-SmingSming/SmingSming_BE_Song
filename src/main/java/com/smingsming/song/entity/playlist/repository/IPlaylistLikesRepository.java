package com.smingsming.song.entity.playlist.repository;


import com.smingsming.song.entity.playlist.entity.PlaylistLikesEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistLikesRepository extends JpaRepository<PlaylistLikesEntity, Long> {

    PlaylistLikesEntity findByUuidAndPlaylistEntityId(String uuid, Long playlistId);
    List<PlaylistLikesEntity> findAllByUuid(String uuid);

    @Query(" select new  com.smingsming.song.entity.playlist.vo.PlaylistVo( " +
            " pl.playlistEntity.id, pl.playlistEntity.title, pl.playlistEntity.playlistThumbnail, " +
            " pl.playlistEntity.uuid, " +
            " case when pl2.id is not null then true else false end ) " +
            " from PlaylistLikesEntity pl " +
            " left join PlaylistLikesEntity  pl2 on pl.playlistEntity.id = pl2.playlistEntity.id and pl2.uuid = :searchUser " +
            " where pl.uuid = :searchedUser ")

    List<PlaylistVo> getAllByUuid(@Param("searchUser") String searchUser, @Param("searchedUser") String searchedUser);
//            " left join PlaylistLikesEntity  pl2 on pl.playlistEntity.id = pl2.playlistEntity.id and pl2.userId = :searchUser " +
//                    " where pl.userId = :searchedUser ")
//    List<PlaylistVo> getAllByUserId(@Param("searchUser") Long userId, @Param("searchedUser") Long userId2);
}
