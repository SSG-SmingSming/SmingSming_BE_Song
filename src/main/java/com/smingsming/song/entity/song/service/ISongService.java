package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.vo.FormalSongAddRequestVo;

public interface ISongService {
    boolean formalSongAdd(FormalSongAddRequestVo requestVo);
}
