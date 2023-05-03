package com.aform.spring_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aform.spring_user.configuration.EncoderConfig;
import com.aform.spring_user.domain.user.User;
import com.aform.spring_user.domain.user.UserRepository;
import com.aform.spring_user.utils.JwtUtil;
import com.aform.spring_user.web.dto.UserDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expireMS = 1000L * 60 ; //1분

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //회원등록
    @Transactional
    public User saveUser(UserDto.RegistRequestDto registRequestDto){
        registRequestDto.setUserPw(passwordEncoder.encode(registRequestDto.getUserPw()));
       return userRepository.save(registRequestDto.toEntity());
        
    }
    //로그인
    @Transactional
    public String userLogin(UserDto.LoginRequestDto loginRequestDto){
        User user = userRepository.findByUserId(loginRequestDto.getUserId());
        if(user == null){
            return "아이디가 존재하지 않습니다.";
        }
        if(!passwordEncoder.matches(loginRequestDto.getUserPw(), user.getUserPw())){// 평문, 암호화 순. 순서 유의
            return "비밀번호가 일치하지 않습니다.";
        }

        String token = JwtUtil.createJwt(user.getUserId(), secretKey, expireMS );
        System.out.println("token  "+token);
        return token;
    }
    //회원정보조회
    @Transactional
    public UserDto.GetUserResponseDto getUser(String userId, Authentication authentication){
        
        User user = userRepository.findByUserId(authentication.getName());
        return UserDto.GetUserResponseDto.builder()
                                        .userId(user.getUserId())
                                        .userPw(user.getUserPw())
                                        .email(user.getEmail())
                                        .name(user.getName())
                                        .phone(user.getPhone())
                                        .address(user.getAddress())
                                        .birth(user.getBirth())
                                        .gender(user.getGender())
                                        .build();

    }

    //회원 삭제
    @Transactional
    public void deleteUser(String userId){
        userRepository.deleteByUserId(userId);
    }

    //아이디 중복 확인
    @Transactional
    public boolean isDuplicatedId(String userId){
        return userRepository.existsByUserId(userId);
    }


}