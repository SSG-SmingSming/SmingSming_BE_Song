package com.smingsming.song.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{      // 기본적으로 사용되는 exception 외에 사용

    private ErrorCode errorCode;
}
