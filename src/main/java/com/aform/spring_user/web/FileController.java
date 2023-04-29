package com.aform.spring_user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aform.spring_user.domain.file.File;
import com.aform.spring_user.service.FileService;
import com.aform.spring_user.web.dto.FileDto;

@Controller
@RequestMapping(path = "/app/file")
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
    public ResponseEntity<File> uploadFile(FileDto.UploadFileRequestDto uploadFileRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.uploadFile(uploadFileRequestDto));
    }

    /*
     * 사진가져오기
     * 
     * @variable userId
     * 
     * @return ok
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<FileDto.FileResponseDto> getFile(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(fileService.getFile(userId));
    }

    /*
     * 사진삭제
     * 
     * @variable userId
     * 
     * @return ok, "deleted"
     */
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "userId") String userId) {
        fileService.deleteFile(userId);
        return ResponseEntity.ok("deleted");
    }
}
