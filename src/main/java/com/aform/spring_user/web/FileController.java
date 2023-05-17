package com.aform.spring_user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aform.spring_user.domain.file.File;
import com.aform.spring_user.service.FileService;
import com.aform.spring_user.web.dto.FileDto;

@RestController
@RequestMapping(path = "/api/file")
public class FileController {
    
    @Autowired
    private FileService fileService;

     /*
     * 사진등록
     * 
     * @RequestBody 
     * 
     * @return 201 CREATED ,
     */
    @PostMapping
    public ResponseEntity<File> uploadFile(@RequestBody FileDto.UploadFileRequestDto uploadFileRequestDto, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.uploadFile(uploadFileRequestDto));
    }

    /*
     * 사진가져오기
     * 
     * 
     * 
     * @return ok
     */
    @GetMapping(path = "/getImg")
    public ResponseEntity<FileDto.FileResponseDto> getFile(Authentication authentication) {
        return ResponseEntity.ok(fileService.getFile(authentication.getName()));
    }

    /*
     * 사진삭제
     * 
     * 
     * 
     * @return ok, "deleted"
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteFile(Authentication authentication) {
        fileService.deleteFile(authentication.getName());
        return ResponseEntity.ok("deleted");
    }
}
