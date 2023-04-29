package com.aform.spring_user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aform.spring_user.domain.user.User;
import com.aform.spring_user.service.UserService;
import com.aform.spring_user.web.dto.UserDto;

@Controller
@RequestMapping(path = "/app/user")
public class UserController {
    @Autowired
    UserService userService;

    /*
     * 회원가입 API
     * 
     * @RequestBody UserDto.registRequestDto
     * 
     * @return 201 CREATED , User
     */
    @PostMapping(path = "")
    public ResponseEntity<User> RegistUser(@RequestBody UserDto.RegistRequestDto regist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(regist));
    }

    /*
     * 아이디 중복확인 API
     * 
     * @variable userId
     * 
     * @return ok
     */
    @GetMapping(path = "/Idcheck/{userId}")
    public ResponseEntity<Boolean> isDuplicatedId(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(userService.isDuplicatedId(userId));
    }

    /*
     * 회원탈퇴 API
     * 
     * @variable userPk
     * 
     * @return ok, "deleted"
     */
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("deleted");
    }

    /*
     * 회원조회 API
     * 
     * @variable userId
     * 
     * @return ok, userDto.GetUserResponseDto
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto.GetUserResponseDto> getUserInfo(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

 
}
//