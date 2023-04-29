package com.aform.spring_user.web.dto;

import com.aform.spring_user.domain.file.File;
import com.aform.spring_user.domain.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UploadFileRequestDto {
        private String userId;
        private String filePath;

        @Builder
        public UploadFileRequestDto(String userId, String filePath) {
            this.userId = userId;
            this.filePath = filePath;
        }       
        
        public File toEntity(User user) {
            return File.builder()
                    .user(user)
                    .filePath(filePath)
                    .build();
        }
    } 
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FileResponseDto {
        private String filePath;

        @Builder
        public FileResponseDto(User user, String filePath) {
            this.filePath = filePath;
        }

    }
}
