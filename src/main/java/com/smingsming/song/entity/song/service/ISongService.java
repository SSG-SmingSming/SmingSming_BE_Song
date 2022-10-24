package com.smingsming.song.entity.song.service;

import com.smingsming.song.entity.song.entity.SongEntity;
import com.smingsming.song.entity.song.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISongService {
    boolean formalSongAdd(FormalSongAddReqVo requestVo);
    boolean customSongAdd(CustomSongAddReqVo requestVo, HttpServletRequest request);
    boolean songDelete(Long id);
    boolean customSongDelete(Long id, HttpServletRequest request);

    SongGetVo songPlay(Long id);
    List<SongGetVo> songSearch(String keyWord, int page, HttpServletRequest request);
    SearchResultVo totalSearch(String keyword, int page, HttpServletRequest request);
}
