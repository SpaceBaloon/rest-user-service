package com.userservice.user;

import com.userservice.role.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 *
 * @author belki
 */
@Entity
@Table( name = "users" )
public class User implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @NotBlank
    @Column( name = "name" )
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @NotBlank
    @Column( name = "login" )
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @NotBlank
    @Pattern( regexp = "((?=.*[A-Z])(?=.*[0-9]).+)" )
    @Column( name = "password" )
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @ManyToMany
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" )
    )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }
    
    public User( Integer id ) {
        this.id = id;
    }

    public User(String login, String password) {        
        this.password = password;
        this.login = login;
    }

    public User( String name, String login, String password ) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj ) return true;
        if( ! (obj instanceof User ) ) return false;
        final User other = (User) obj;
        if( other.id != this.id && ( this.id == null || !this.id.equals( other.id ) ) )
            return false;        
        if( other.name != this.name && ( this.name == null || !this.name.equals( other.name ) ) )
            return false;
        if( other.login != this.login && ( this.login == null || !this.login.equals( other.login ) ) )
            return false;
        if( other.password != this.password && ( this.password == null || !this.password.equals( other.password ) ) )
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 97 * hash + id;
        hash = 89 * hash + ( name != null ? name.hashCode() : 0 );
        hash = 47 * hash + ( login != null ? login.hashCode() : 0 );
        hash = 53 * hash + ( password != null ? password.hashCode() : 0 );
        return hash;
    }

    @Override
    public String toString() {
        return "User["
                + "id=" + id + ", "
                + "name=" + name + ", "
                + "login=" + login + ", "
                + "password=" + password
                + "]";
    }
    
}
