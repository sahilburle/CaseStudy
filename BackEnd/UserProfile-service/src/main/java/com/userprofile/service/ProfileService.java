package com.userprofile.service;

import java.util.List;

import com.userprofile.model.UserProfile;

public interface ProfileService {
	
	UserProfile addNewCustomProfile(UserProfile user);
	
	List<UserProfile> getAllProfiles();
	
	UserProfile getByProfile(String profileId);
	
	void updateProfile(UserProfile user);
	
	void deleteProfile(String profileId);
	
	void addNewMerchantProfile(UserProfile user);
	
	void addNewdeliveryProfile(UserProfile user);
	
	UserProfile findByMobileNo(Long mobilenumber);
	
	UserProfile getByuserName(String userName);
}
