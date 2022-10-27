package com.smingsming.song.entity.playlist.repository;

import com.smingsming.song.entity.playlist.entity.PlaylistEntity;
import com.smingsming.song.entity.playlist.vo.PlaylistCountVo;
import com.smingsming.song.entity.playlist.vo.PlaylistVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    @Query( value = "select new com.smingsming.song.entity.playlist.vo.PlaylistVo( " +
            " p.id, p.title, p.playlistThumbnail, p.userId, " +
            " case when pl.id is not null then true else false end " +
            ") " +
            " from PlaylistEntity p " +
            " left join PlaylistLikesEntity pl on p.id = pl.playlistEntity.id " +
            " where p.title like :keyword")
    List<PlaylistVo> findAllByTitleContains(@Param("keyword") String keyword, Pageable pr);

    @Query( value = "select new com.smingsming.song.entity.playlist.vo.PlaylistVo( " +
            " p.id, p.title, p.playlistThumbnail, p.userId, " +
            " case when pl.id is not null then true else false end " +
            " ) " +
            " from PlaylistEntity p " +
            " left join PlaylistLikesEntity pl on p.id = pl.playlistEntity.id and pl.userId = :searchUser " +
            " where p.userId = :searchedUser ")
            
    List<PlaylistVo> getAllByUserId(@Param("searchUser") Long searchUser, @Param("searchedUser") Long searchedUser, Pageable pr);
    Long countByUserId(Long userId);

}
