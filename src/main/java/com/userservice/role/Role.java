package com.userservice.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.user.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Belkin Sergei
 */
@Entity
@Table( name = "roles" )
public class Role implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @JsonIgnore
    @ManyToMany
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn( name = "role_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" )
    )
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }    

    private Role() {
    }
    
    private Role( Integer id ) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role[" 
                + "id=" + id + ", "
                + "name=" + name
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !( obj instanceof Role ) ) return false;
        Role other = (Role) obj;
        if( this.id != other.id && ( this.id == null ||! this.id.equals( other.id ) ) ) return false;
        if( this.name != other.name && ( this.name == null || !this.name.equals( other.name ) ) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 29;
        hash = 107 * hash + id;
        hash = 93 * hash + ( name == null ? 0 : name.hashCode() );
        return hash;
    }
    
}
