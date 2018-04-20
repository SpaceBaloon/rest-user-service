package com.userservice.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Belkin Sergei
 */
public interface UserRepository extends JpaRepository<User, Integer>{    
}
