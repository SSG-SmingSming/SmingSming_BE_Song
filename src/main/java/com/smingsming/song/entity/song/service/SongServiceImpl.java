package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.album.vo.AlbumVo;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.artist.vo.ArtistVo;
import com.smingsming.song.entity.playlist.repository.IPlaylistTrackRepository;
import com.smingsming.song.entity.song.client.UserServiceClient;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongLikesRepository;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.*;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongServiceImpl implements ISongService {

    private final ISongRepository iSongRepository;
    private final ISongLikesRepository iSongLikesRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;
    private final UserServiceClient userServiceClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final IPlaylistTrackRepository iPlaylistTrackRepository;


    // 공식 음원 등록
    @Override
    public boolean formalSongAdd(FormalSongAddReqVo requestVo) {
        Optional<AlbumEntity> album = iAlbumRepository.findById(requestVo.getAlbum());
        Optional<ArtistEntity> artist = iArtistRepository.findById(requestVo.getArtist());

        if(album.isEmpty() || artist.isEmpty()) {
            return false;
        }

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        SongEntity mapSong = mapper.map(requestVo, SongEntity.class);
        mapSong.setFormal(true);
        mapSong.setAlbumEntity(album.get());
        mapSong.setArtist(artist.get());

        iSongRepository.save(mapSong);

        return true;
    }

    // 사용자 음원 등록
    @Override
    public boolean customSongAdd(CustomSongAddReqVo requestVo, HttpServletRequest request) {
        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        UserDetailVo user = userServiceClient.getUuid(uuid);

        AlbumEntity album = AlbumEntity.builder()
                .title(requestVo.getSongName())
                .albumThumbnail(requestVo.getSongThumbUri())
                .releaseDate(LocalDate.now())
                .build();

        iAlbumRepository.save(album);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        SongEntity mapSong = mapper.map(requestVo, SongEntity.class);
        mapSong.setFormal(false);
        mapSong.setAlbumEntity(album);
        mapSong.setUuid(uuid);

        iSongRepository.save(mapSong);

        return true;
    }

    // 사용자 음원 목록 조회
    @Override
    public List<SongVo> customSongGet(String searchedUser, HttpServletRequest request) {
        String searchUser = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        List<SongVo> songVoList = iSongRepository.getAllByIsFormalAndSongId(searchUser, searchedUser);
        List<SongVo> returnVo = new ArrayList<>();

        songVoList.forEach(v -> {
            UserDetailVo user = userServiceClient.getUuid(v.getUuid());
            returnVo.add(SongVo.builder()
                            .id(v.getId())
                            .uuid(v.getUuid())
                            .albumId(v.getAlbumId())
                            .artistName(user.getNickName())
                            .songThumbnail(v.getSongThumbnail())
                            .songName(v.getSongName())
                            .songUri(v.getSongUri())
                            .isLike(v.isLike())
                            .isFormal(v.isFormal())
                    .build());
        });

        return returnVo;
    }

    // 공식 음원 삭제
    @Override
    @Transactional
    public boolean songDelete(Long id) {
        SongEntity songEntity = iSongRepository.findById(id).orElseThrow();

        iSongRepository.delete(songEntity);
        iPlaylistTrackRepository.deleteAllBySongId(id);

        return true;
    }

    // 사용자 등록 음원 삭제
    @Override
    public boolean customSongDelete(Long id, HttpServletRequest request) {
        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));
        UserDetailVo user = userServiceClient.getUuid(uuid);

        SongEntity songEntity = iSongRepository.findById(id).orElseThrow();

        if(songEntity.getUuid().equals(user.getUuid())) {
            iSongRepository.delete(songEntity);
            return true;
        }
        return false;
    }

    // 음원 재생
    @Override
    public SongVo songPlay(Long songId, HttpServletRequest request) {
        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        SongEntity songEntity = iSongRepository.findById(songId).orElseThrow();

        SongVo returnVo = SongVo.builder()
                .id(songEntity.getId())
                .albumId(songEntity.getAlbumEntity().getId())
                .songThumbnail(songEntity.getAlbumEntity().getAlbumThumbnail())
                .songUri(songEntity.getSongUri())
                .songName(songEntity.getSongName())
                .isFormal(songEntity.isFormal())
                .isLike(iSongLikesRepository.existsBySongEntityIdAndUuid(songId, uuid))
                .build();

        if(songEntity.isFormal()) {
            returnVo.setArtistName(songEntity.getArtist().getName());
        }else {
            UserDetailVo user = userServiceClient.getUuid(uuid);
            returnVo.setArtistName(user.getNickName());
        }

        return returnVo;
    }

    // 음원 검색
    @Override
    public List<SongVo> songSearch(String keyword, int page, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        List<SongVo> songList = new ArrayList<>();
        Pageable pr = PageRequest.of(page - 1 , 20, Sort.by("id").descending());

        keyword = "%" + keyword + "%";

        songList = iSongRepository.getSongListByKeyword(pr, keyword, uuid);

        return songList;
    }

    // 전체 검색
    @Override
    public SearchResultVo totalSearch(String keyword, int page, HttpServletRequest request) {

        String uuid = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        Pageable pr = PageRequest.of(page - 1 , 20, Sort.by("id").descending());

        ModelMapper mapper = new ModelMapper();

        List<ArtistEntity> artistList = iArtistRepository.findAllByNameContains(pr, keyword);
        List<ArtistVo> artistVoList = new ArrayList<>();

        artistList.forEach(v -> {
            artistVoList.add(mapper.map(v, ArtistVo.class));
        });

        List<UserVo> userList = userServiceClient.userSearch(keyword, page);

        keyword = "%" + keyword + "%";

        List<AlbumEntity> albumList = iAlbumRepository.getAlbumListByKeyword(pr, keyword);
        List<AlbumVo> albumVoList = new ArrayList<>();

        albumList.forEach(v -> {
            albumVoList.add(mapper.map(v, AlbumVo.class));
        });

        List<SongVo> songList = iSongRepository.getSongListByKeyword(pr, keyword, uuid);

        SearchResultVo result = new SearchResultVo();
        result.setSongList(songList);
        result.setAlbumList(albumVoList);
        result.setUserList(userList);
        result.setArtistList(artistVoList);

        return result;
    }
}
