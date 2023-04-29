package com.aform.spring_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.spring_user.domain.user.User;
import com.aform.spring_user.domain.user.UserRepository;
import com.aform.spring_user.web.dto.UserDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //회원등록
    @Transactional
    public User saveUser(UserDto.RegistRequestDto registRequestDto){
       return userRepository.save(registRequestDto.toEntity());
        
    }

    //회원정보조회
    @Transactional
    public UserDto.GetUserResponseDto getUser(String userId){
        User user = userRepository.findByUserId(userId);
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
        return userRepository.existsByUserId(userId)록
    }


}