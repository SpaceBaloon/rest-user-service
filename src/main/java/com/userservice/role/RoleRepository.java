package com.userservice.role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Belkin Sergei.
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName( String name );
}
