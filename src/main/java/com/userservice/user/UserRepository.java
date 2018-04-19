package com.userservice.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author belki
 */
public interface UserRepository extends JpaRepository<User, Integer>{    
}
