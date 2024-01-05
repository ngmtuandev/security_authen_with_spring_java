package com.security_practical.security_practical.repositories;

import com.security_practical.security_practical.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
