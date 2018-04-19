package com.userservice.user;

import com.userservice.exceptions.UserNotFoundException;
import com.userservice.role.RoleRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Belkin Sergei.
 */
@RestController
@RequestMapping("/userservice/user")
public class UserController {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController( UserRepository userRepository, RoleRepository roleRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    @GetMapping( "/get/{id}" )
    public User getUser( @PathVariable(value = "id") Integer id ) {
        return getUserById(id);
    }
    
    @PostMapping( "/add" )
    public User addUser( @Valid @RequestBody User user ) {
        return userRepository.save( user );
    }
    
    @PutMapping( "/edit/{id}" )
    public User editUser( @PathVariable( name="id" ) Integer id, 
            @Valid @RequestBody User user ) {
        User oldUser = getUserById(id);
        oldUser.setName( user.getName() );
        oldUser.setLogin( user.getLogin() );
        oldUser.setPassword( user.getPassword() );
        oldUser.setRoles( user.getRoles() );   
        user = userRepository.save( oldUser );
        return user;
    }
    
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<?> deleteUser( @PathVariable( name="id" ) Integer id ) {
        User user = getUserById(id);
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping( "/list" )
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    protected User getUserById( Integer id ) {
        return userRepository.findById(id).orElseThrow( () -> new UserNotFoundException(id) );
    }
    
}
