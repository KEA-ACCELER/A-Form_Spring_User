package com.aform.spring_user.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.spring_user.domain.user.User;

public interface FileRepository extends JpaRepository<File, Long>{
    
    public File findByUser(User user);

    public void deleteByUser(User user);

}
