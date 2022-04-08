package com.userprofile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.userprofile.model.UserProfile;

@Repository
public interface ProfileRepo extends MongoRepository<UserProfile, String>{
	
	UserProfile findBymobilenumber(long mobilenumber);
	UserProfile findByfullName(String userfullname);
}
