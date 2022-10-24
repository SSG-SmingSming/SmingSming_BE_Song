package com.smingsming.song.entity.song.client;

import com.smingsming.song.entity.song.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-server")
public interface UserServiceClient {

    @GetMapping("/user/get/{id}")
    UserVo getUser(@PathVariable(value = "id") Long id);

}
