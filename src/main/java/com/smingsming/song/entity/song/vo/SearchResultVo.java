package com.smingsming.song.entity.song.vo;


import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.vo.ArtistVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultVo {
    List<SongVo> songList;
    List<AlbumVo> albumList;
    List<ArtistVo> artistList;
    List<UserVo> userList;
}
