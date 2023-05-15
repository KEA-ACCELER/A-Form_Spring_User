package com.aform.spring_user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    // @Autowired
    // JwtEncoder jwtEncoder;

    /*
     * 회원가입 API
     * 
     * @RequestBody UserDto.registRequestDto
     * 
     * @return 201 CREATED , User
     */
    @PostMapping(path = "/join")
    public ResponseEntity<User> RegistUser(@RequestBody UserDto.RegistRequestDto regist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(regist));

    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @GetMapping(path = "/test2")
    public ResponseEntity<String> test2() {
        return test();
    }

    /**
     * 로그인
     * 
     * @RequestBody UserDto.loginRequestDto
     * 
     * @return 200 ok
     * 
     */
    @PostMapping(path = "/login")
    public ResponseEntity<String> userLogin(@RequestBody UserDto.LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.userLogin(loginRequestDto));
    }

    /*
     * 아이디 중복확인 API
     * 
     * @variable userId
     * 
     * @return ok
     */
    @GetMapping(path = "/idcheck/{userId}")
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
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication);
        return ResponseEntity.ok("deleted");
    }

    /*
     * 회원조회 API
     * 
     * @variable userId
     * 
     * @return ok, userDto.GetUserResponseDto
     */
    @GetMapping(path = "/info")
    public ResponseEntity<UserDto.GetUserResponseDto> getUserInfo(Authentication authentication) {
        return ResponseEntity.ok(userService.getUser(authentication));
    }


  

}
//