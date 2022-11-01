package com.smingsming.song.exception;

public enum ExceptionCode {

    /* SUCCESS */
    SUCCESS_CODE(200,"S000", "SUCCESS"),

    /* COMMON */
    INVALID_CODE(400, "C001", ""),
    RESOURCE_NOT_FOUND(204, "C002", ""),
    EXPIRED_CODE(400, "C003", ""),
    FAIL_MESSAGE(400, "COO4", "message transmission failure"),


    /* 6000: Token 관련 */
    UNKNOWN_ERROR(600, "T001", "\n" + "The token does not exist."),
    TOKEN_WRONG_TYPE(601, "T002", "The token is malformed."),
    TOKEN_EXPIRED(602, "T003", "The token has expired."),
    TOKEN_UNSUPPORTED(603, "T004", "The token is of an unsupported format."),
    ACCESS_DENIED(604, "T005", "접근할 수 없습니다.")

    ;

    private final String code;
    private final String message;
    private int status;

    ExceptionCode(int status, String code, String message) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
