package com.userservice;

import com.userservice.role.*;
import com.userservice.user.*;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestUserServiceApplication {

    public static void main(String[] args) {
            SpringApplication.run(RestUserServiceApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init( UserRepository userRepository, 
            RoleRepository roleRepository ) {
        return (args) -> {
            Arrays.asList("admin,programmer,user,villain".split( "," ) )
                    .forEach(  
                            ( name ) -> {
                                Role role = roleRepository.findByName(name);
                                if( role != null ) {
                                    System.out.println( "Role with name " + name + " already exists." );
                                    roleRepository.delete(role);
                                    System.out.println("Role was removed.");
                                }
                                System.out.println("Create new role with name " + name );
                                role = new Role( name );
                                role = roleRepository.save( role );
                                System.out.println("Role was successfully saved " + role.toString() );
                            }
                    );
        };
    }
    
}
