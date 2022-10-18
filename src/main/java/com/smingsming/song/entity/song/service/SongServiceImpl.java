package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.album.entity.AlbumEntity;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.artist.entity.ArtistEntity;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.song.client.UserServiceClient;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.*;
import com.smingsming.song.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements ISongService {

    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;
    private final UserServiceClient userServiceClient;
    private final JwtTokenProvider jwtTokenProvider;


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

    @Override
    public boolean customSongAdd(CustomSongAddReqVo requestVo, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        UserVo user = userServiceClient.getUser(userId);

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
        mapSong.setUserId(user.getId());

        iSongRepository.save(mapSong);

        return true;
    }


    @Override
    public boolean songDelete(Long id) {
        SongEntity songEntity = iSongRepository.findById(id).orElseThrow();

        iSongRepository.delete(songEntity);

        return true;
    }

    @Override
    public boolean customSongDelete(Long id, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        UserVo user = userServiceClient.getUser(userId);

        SongEntity songEntity = iSongRepository.findById(id).orElseThrow();

        if(songEntity.getUserId() == user.getId()) {
            iSongRepository.delete(songEntity);
            return true;
        }
        return false;
    }

    @Override
    public SongVo songPlay(Long id) {
        SongEntity songEntity = iSongRepository.findById(id).orElseThrow();

        SongVo returnVo = new ModelMapper().map(songEntity, SongVo.class);

        AlbumEntity album = iAlbumRepository.findById(songEntity.getAlbumEntity().getId()).orElseThrow();
        returnVo.setAlbumName(album.getTitle());
        if(songEntity.isFormal()) {
            ArtistEntity artist = iArtistRepository.findById(songEntity.getArtist().getId()).orElseThrow();

            returnVo.setArtistName(artist.getName());
        }else {
            UserVo user = userServiceClient.getUser(songEntity.getUserId());
            returnVo.setArtistName(user.getNickName());
        }

        return returnVo;
    }

    @Override
    public List<SongVo> songSearch(String keyword, int page) {

        List<SongVo> songList = new ArrayList<>();
        Pageable pr = PageRequest.of(page - 1 , 20, Sort.by("id").descending());

        keyword = "%" + keyword + "%";

        songList = iSongRepository.getSongListByKeyword(pr, keyword);

        return songList;
    }
}
