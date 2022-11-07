package com.smingsming.song.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum ErrorCode {     // 핵심. 모든 예외 케이스를 이곳에서 관리

    /* 400 BAD_REQUEST : 잘못된 요청, 클라이언트 오류 */
    /* User */
    USER_JOIN_FAILED (BAD_REQUEST, "회원가입에 실패하였습니다."),
    EXIST_EMAIL (BAD_REQUEST, "이미 존재하는 이메일입니다."),
    EXIST_NICKNAME (BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    EXIST_PASSWORD (BAD_REQUEST, "이미 사용 중인 비밀번호입니다."),
    PRIVACY_READ_FAILED (BAD_REQUEST, "개인정보 조회에 실패하였습니다."),
    PASSWORD_UPDATE_FAILED (BAD_REQUEST, "비밀번호 변경을 실패하였습니다."),
    NICKNAME_UPDATE_FAILED (BAD_REQUEST, "닉네임 변경을 실패하였습니다."),
    THUMBNAIL_UPDATE_FAILED (BAD_REQUEST, "프로필 사진 변경을 실패하였습니다."),

    /* Album */
    ALBUM_REGISTER_FAILED (BAD_REQUEST, "앨범 등록을 실패하였습니다."),
    ALBUM_READ_FAILED (BAD_REQUEST, "조회할 수 없는 앨범입니다."),
    ALBUM_SEARCH_FAILED (BAD_REQUEST, "해당 앨범을 찾을 수 없습니다."),
    ALBUM_DETAIL_READ_FAILED (BAD_REQUEST, "해당 앨범의 상세 정보를 조회할 수 없습니다."),
    ALBUM_DETAIL_UPDATE_FAILED (BAD_REQUEST, "앨범 정보를 수정할 수 없습니다."),
    ALBUM_DELETE_FAILED (BAD_REQUEST, "앨범 삭제를 실패하였습니다."),

    /* Artist */
    ARTIST_REGISTER_FAILED (BAD_REQUEST, "아티스트 등록을 실패하였습니다."),
    ARTIST_READ_FAILED (BAD_REQUEST, "조회할 수 없는 아티스트입니다."),
    ARTIST_ALBUM_READ_FAILED (BAD_REQUEST, "해당 아티스트의 앨범을 찾을 수 없습니다."),
    ARTIST_SONG_READ_FAILED (BAD_REQUEST, "해당 아티스트의 음원을 찾을 수 없습니다."),
    ARTIST_DETAIL_READ_FAILED (BAD_REQUEST, "해당 아티스트의 상세 정보를 조회할 수 없습니다."),
    ARTIST_DETAIL_UPDATE_FAILED (BAD_REQUEST, "아티스트 정보를 수정할 수 없습니다."),
    ARTIST_DELETE_FAILED (BAD_REQUEST, "아티스트 삭제를 실패하였습니다."),

    /* Playlist */
    PLAYLIST_REGISTER_FAILED (BAD_REQUEST, "플레이리스트 생성을 실패하였습니다."),
    PLAYLIST_READ_FAILED (BAD_REQUEST, "조회할 수 없는 플레이리스트입니다."),
    PLAYLIST_NOT_EXISTED (BAD_REQUEST, "플레이리스트가 존재하지 않습니다."),
    PLAYLIST_ADD_TRACK_FAILED (BAD_REQUEST, "플레이리스트에 수록곡을 추가할 수 없습니다."),
    PLAYLIST_SONG_READ_FAILED (BAD_REQUEST, "플레이리스트 내 수록곡을 조회할 수 없습니다."),
    PLAYLIST_SONG_DELETE_FAILED (BAD_REQUEST, "플레이리스트 내 수록곡을 삭제할 수 없습니다."),
    PLAYLIST_DETAIL_READ_FAILED (BAD_REQUEST, "해당 아티스트의 상세 정보를 조회할 수 없습니다."),
    PLAYLIST_DETAIL_UPDATE_FAILED (BAD_REQUEST, "플레이리스트 정보를 수정할 수 없습니다."),
    PLAYLIST_DELETE_FAILED (BAD_REQUEST, "플레이리스트 삭제를 실패하였습니다."),

    /* Playlist 좋아요 */
    PLAYLIST_LIKE_ADD_FAILED (BAD_REQUEST, "플레이리스트에 좋아요를 실패했습니다.") ,
    PLAYLIST_LIKE_READ_FAILED (BAD_REQUEST, "좋아요 한 플레이리스트를 조회할 수 없습니다."),
    PLAAYLIST_LIKE_DELETE_FAILED (BAD_REQUEST, "좋아요를 취소할 수 없습니다."),

    /* Song */
    SONG_FORMAL_ADD_FAILED (BAD_REQUEST, "공식 음원 등록을 실패했습니다.") ,
    SONG_CUSTOM_ADD_FAILED (BAD_REQUEST, "사용자 음원 등록을 실패했습니다."),
    SONG_NOT_EXISTED (BAD_REQUEST, "음원이 존재하지 않습니다."),
    SONG_DELETE_FAILED (BAD_REQUEST, "음원 삭제를 실패하였습니다."),
    SONG_PLAY_FAILED (BAD_REQUEST, "음원을 재생할 수 없습니다."),
    SONG_SEARCH_FAILED (BAD_REQUEST, "음원 검색을 할 수 없습니다."),
    SEARCH_FAILED (BAD_REQUEST, "검색을 할 수 없습니다."),

    /* Song 좋아요 */
    SONG_LIKE_ADD_FAILED (BAD_REQUEST, "음원에 좋아요를 실패했습니다.") ,
    SONG_LIKE_READ_FAILED (BAD_REQUEST, "좋아요 한 음원을 조회할 수 없습니다."),
    SONG_LIKE_DELETE_FAILED (BAD_REQUEST, "좋아요를 취소할 수 없습니다."),


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    TOKEN_WRONG_TYPE(UNAUTHORIZED, "잘못된 형식의 토큰입니다."),
    TOKEN_EXPIRED(UNAUTHORIZED, "만료된 토큰입니다."),
    TOKEN_UNSUPPORTED(UNAUTHORIZED, "지원하지 않는 형식의 토큰입니다."),
    ACCESS_DENIED(UNAUTHORIZED, "접근할 수 없습니다."),
    TOKEN_SIGNATURE_ERROR(UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    TOKEN_NULL(UNAUTHORIZED, "토큰이 null이 아닙니다."),
    //    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "이미 존재하는 데이터입니다."),


    /* 500 INTERNAL_SERVER_ERROR : 서버 에러 */
    USER_SERVER_ERROR(INTERNAL_SERVER_ERROR, "USER 서버에서 요청을 이행할 수 없습니다."),
    USERSERVICE_SERVER_ERROR(INTERNAL_SERVER_ERROR, "USER SERVICE 서버에서 요청을 이행할 수 없습니다."),
    CHAT_SERVER_ERROR(INTERNAL_SERVER_ERROR, "CHAT 서버에서 요청을 이행할 수 없습니다."),
    SONG_SERVER_ERROR(INTERNAL_SERVER_ERROR, "SONG 서버에서 요청을 이행할 수 없습니다."),


    /* 503 SERVICE_UNAVAILABLE : 서버 지연 */
    USER_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "USER 서버가 지연되는 중입니다."),
    USERSERVICE_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "USER SERVICE 서버가 지연되는 중입니다."),
    CHAT_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "CHAT 서버가 지연되는 중입니다."),
    SONG_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "SONG 서버가 지연되는 중입니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
