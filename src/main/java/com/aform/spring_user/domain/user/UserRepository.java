package com.aform.spring_user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserPk(long userPk);

    public User findByUserId(String userId);

    public boolean existsByUserId(String userId);

    public void deleteByUserId(String userId);
    

}
