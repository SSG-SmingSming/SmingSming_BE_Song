package com.smingsming.song.global.utils.s3;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FileInfoDto {
    private String id;
    private String name;
    private String format;
    private String localPath;
    private String remotePath;
    private long bytes;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();


    public static FileInfoDto multipartOf(MultipartFile multipartFile, String fileDiv) {
        final String fileId = MultipartUtil.createFileUUID();
        final String fileName = multipartFile.getOriginalFilename();
        final String format = fileName.substring(fileName.lastIndexOf('.') + 1);

        return FileInfoDto.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .localPath(MultipartUtil.createLocalPath(fileId, format))
                .remotePath(MultipartUtil.createRemotePath(fileDiv, fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }
}


