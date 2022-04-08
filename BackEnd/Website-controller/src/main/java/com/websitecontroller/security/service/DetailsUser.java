package com.websitecontroller.security.service;

import com.websitecontroller.security.modelclass.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsUser implements UserDetails {

    private String userName;
    private String pass;
    private String profileId;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public DetailsUser(Profile user)
    {
        this.userName = user.getFullName();
        this.pass = user.getPassword();
        this.profileId = user.getProfileId();
        this.authorities = Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        System.out.println("User Detail " + this.userName + " " + this.pass + " " + this.authorities + " ");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getProfileId() {
        return profileId;
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
