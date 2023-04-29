package com.aform.spring_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.spring_user.domain.file.File;
import com.aform.spring_user.domain.file.FileRepository;
import com.aform.spring_user.domain.user.User;
import com.aform.spring_user.domain.user.UserRepository;
import com.aform.spring_user.web.dto.FileDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
    
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public File uploadFile(FileDto.UploadFileRequestDto uploadFileRequestDto) {
        User user = userRepository.findByUserId(uploadFileRequestDto.getUserId());
        return fileRepository.save(uploadFileRequestDto.toEntity(user));
    }

    @Transactional
    public FileDto.FileResponseDto getFile(String userId) {
        User user = userRepository.findByUserId(userId);
        File file = fileRepository.findByUser(user);
        return FileDto.FileResponseDto.builder()
                .filePath(file.getFilePath())
                .build();
    }

    @Transactional
    public void deleteFile(String userId) {
        User user = userRepository.findByUserId(userId);
        fileRepository.deleteByUser(user);
    }
}
