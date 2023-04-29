package com.aform.spring_user.domain.file;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.spring_user.BaseTimeEntity;
import com.aform.spring_user.domain.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file")
@Component
@Getter
public class File extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_pk")
    private Long filePk;

    @Column(name = "file_path", length = 100, nullable = false)
    private String filePath;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Builder
    public File(String filePath, User user){
        this.filePath = filePath;
        this.user = user;
    }
}
