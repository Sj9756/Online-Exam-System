package com.santosh.oes.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


public class UserDetailsOes implements UserDetails {
    UserOes userOes;

    public UserDetailsOes(UserOes userOes) {
        this.userOes = userOes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userOes.getRoles().toString()));
    }

    @Override
    public String getPassword() {
        return userOes.getPassword();
    }

    @Override
    public String getUsername() {
        return userOes.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userOes.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userOes.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userOes.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userOes.isEnabled();
    }
}
