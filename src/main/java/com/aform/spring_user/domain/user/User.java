package com.aform.spring_user.domain.user;


import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.aform.spring_user.BaseTimeEntity;
import com.aform.spring_user.domain.file.File;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Component
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk")
    private Long userPk;

    @Column(name = "user_id", length = 30, nullable = false, unique = true)
    private String userId;

    @Column(name = "user_pw", length = 1000, nullable = false)
    private String userPw;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "birth")
    private LocalDateTime birth; // DATETIME으로 자동 매핑 됨.

    @Column(name = "gender")
    private Boolean gender;


    @Builder
    public User (String userId, String userPw, String email, String name, String phone, String address, LocalDateTime birth, Boolean gender){
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
    }




}
