package com.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userprofile.model.Role;
import com.userprofile.model.UserProfile;
import com.userprofile.repository.ProfileRepo;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	ProfileRepo profileRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public UserProfile addNewCustomProfile(UserProfile user) {
		user.setRole(Role.Customer);
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		profileRepo.save(user);
		this.restTemplate.postForObject("http://Cart-service/mycart/addCart/" + user.getProfileId(), user, Void.class);
		return user;
	}

	@Override
	public List<UserProfile> getAllProfiles() {
		
		return profileRepo.findAll();
	}

	@Override
	public UserProfile getByProfile(String profileId) {
		Optional<UserProfile> abcd = profileRepo.findById(profileId);
		UserProfile userFound = abcd.get();
		return userFound;
	}

	@Override
	public void updateProfile(UserProfile user) {
		
		profileRepo.save(user);
	}

	@Override
	public void deleteProfile(String profileId) {
		
		profileRepo.deleteById(profileId);	
	}

	@Override
	public void addNewMerchantProfile(UserProfile user) {
		
		user.setRole(Role.merchant);
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		profileRepo.save(user);
	}

	@Override
	public void addNewdeliveryProfile(UserProfile user) {
		
		user.setRole(Role.deliveryAgent);
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		profileRepo.save(user);
	}

	@Override
	public UserProfile findByMobileNo(Long mobilenumber) {
		
		return profileRepo.findBymobilenumber(mobilenumber);
	}

	@Override
	public UserProfile getByuserName(String userName) {
		
		return profileRepo.findByfullName(userName);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
}
