package com.userservice.role;

import com.userservice.exceptions.UserNotFoundException;
import com.userservice.user.User;
import java.util.Collection;
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
@RestController()
@RequestMapping( "/userservice/role" )
public class RoleController {
    
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @GetMapping( "/list" )
    public Collection<Role> listRoles() {
        return roleRepository.findAll();
    }
    
    @GetMapping( "/get/{id}" )
    public Role getRole( @PathVariable(value = "id") Integer id ) {
        return getRoleById(id);
    }
    
    @PostMapping( "/add" )
    public Role addRole( @Valid @RequestBody Role role ) {
        return roleRepository.save( role );
    }
    
    @PutMapping( "/edit/{id}" )
    public Role editUser( @PathVariable( name="id" ) Integer id, 
            @Valid @RequestBody Role role ) {
        Role oldRole = getRoleById(id);
        oldRole.setName( role.getName() );
//        oldRole.setUsers(role.getUsers() );
        return roleRepository.save( oldRole );        
    }
    
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<?> deleteUser( @PathVariable( name="id" ) Integer id ) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
        return ResponseEntity.ok().build();
    }
    
    protected Role getRoleById( Integer id ) {
        return roleRepository.findById(id).orElseThrow( () -> new UserNotFoundException(id) );
    }
    
}
