package com.services.autoparts.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImplemented implements UserDetails {
    private Long id;
    @JsonIgnore
    private String password;
    private String username;
    private List<GrantedAuthority> authorities;

    public UserDetailsImplemented(User user) {
        List<Role> roles = user.getRoles();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Role role: roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = authorityList;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
