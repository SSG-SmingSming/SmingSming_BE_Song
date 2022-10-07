package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.album.entity.Album;
import com.smingsming.song.entity.album.repository.IAlbumRepository;
import com.smingsming.song.entity.artist.entity.Artist;
import com.smingsming.song.entity.artist.repository.IArtistRepository;
import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.repository.ISongRepository;
import com.smingsming.song.entity.song.vo.FormalSongAddReqVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements ISongService {

    private final ISongRepository iSongRepository;
    private final IAlbumRepository iAlbumRepository;
    private final IArtistRepository iArtistRepository;

    @Override
    public boolean formalSongAdd(FormalSongAddReqVo requestVo) {
        Optional<Album> album = iAlbumRepository.findById(requestVo.getAlbum());
        Optional<Artist> artist = iArtistRepository.findById(requestVo.getArtist());

        if(album.isEmpty() || artist.isEmpty()) {
            return false;
        }

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        SongEntity mapSong = mapper.map(requestVo, SongEntity.class);
        mapSong.setFormal(true);
        mapSong.setAlbum(album.get());
        mapSong.setArtist(artist.get());

        iSongRepository.save(mapSong);

        return true;
    }
}
