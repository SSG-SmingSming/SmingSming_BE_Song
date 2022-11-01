package com.smingsming.song.entity.song.client;

import com.smingsming.song.entity.song.vo.UserDetailVo;
import com.smingsming.song.entity.song.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name = "user-server")
public interface UserServiceClient {

    @GetMapping("/user/get/{uuid}")
    UserDetailVo getUuid(@PathVariable(value = "uuid") String uuid);

    @GetMapping("/user/search")
    List<UserVo> userSearch(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                            @RequestParam(name = "page", defaultValue = "1") int page);
}
