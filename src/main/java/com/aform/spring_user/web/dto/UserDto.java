package com.aform.spring_user.web.dto;

import java.time.LocalDateTime;

import com.aform.spring_user.domain.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetUserResponseDto {
        private String userId ;
        private String userPw;
        private String email;
        private String name;
        private String phone;
        private String address;
        private LocalDateTime birth;
        private Boolean gender;

        @Builder
        public GetUserResponseDto(String userId, String userPw, String email, String name, String phone, String address, LocalDateTime birth, Boolean gender){
            this.userId = userId;
            this.userPw = userPw;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.birth = birth;
            this.gender = gender;
            this.name = name;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegistRequestDto {
        private String userId;
        @Setter
        private String userPw;
        private String email;
        private String name;
        private String phone;
        private String address;
        private LocalDateTime birth; 
        private Boolean gender;

        public User toEntity(){
            return User.builder()
                        .userId(this.userId)
                        .userPw(this.userPw)
                        .email(this.email)
                        .name(this.name)
                        .phone(this.phone)
                        .address(this.address)
                        .birth(this.birth)
                        .build();
        }        
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateProfileRequestDto {
        private String userId;
        private String profile;

        @Builder
        public UpdateProfileRequestDto(String userId, String profile){
            this.userId = userId;
            this.profile = profile;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginRequestDto{
        private String userId;
        private String userPw;
    }
}