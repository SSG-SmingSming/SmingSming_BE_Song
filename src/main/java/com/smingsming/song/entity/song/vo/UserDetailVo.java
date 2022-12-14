package com.smingsming.song.entity.song.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailVo {
    private String id;
    private String userEmail;
    private String nickName;
    private String userThumbnail;
}
