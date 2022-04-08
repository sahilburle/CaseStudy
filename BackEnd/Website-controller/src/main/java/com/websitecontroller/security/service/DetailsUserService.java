package com.websitecontroller.security.service;

import com.websitecontroller.security.modelclass.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DetailsUserService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public DetailsUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        Profile user = restTemplate.getForObject("http://userProfile-service/profile/getprofile/" + userName, Profile.class);
        System.out.println(user);
        return new DetailsUser(user);
    }
}
