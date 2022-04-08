package com.userprofile.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userprofile.model.UserProfile;
import com.userprofile.service.ProfileService;

@RestController
@CrossOrigin
@RequestMapping("profile")
public class ProfileResource {
	
	@Autowired
	ProfileService profileService;
	
	//adding customer
	@PostMapping("/addCustomer")
	UserProfile addNewCustomerProfile(@RequestBody UserProfile user) {
		return profileService.addNewCustomProfile(user);
	}
	
	//adding merchant
	@PostMapping("/addMerchant")
	void addNewMerchantProfile(@RequestBody UserProfile user) {
		profileService.addNewMerchantProfile(user);
	}
	
	//adding delivery agent
	@PostMapping("/addDeliveryAgent")
	void addNewDeliveryProfile(@RequestBody UserProfile user) {
		profileService.addNewdeliveryProfile(user);
	}
	
	//to delete profile
	@DeleteMapping("/deleteProfile/{userId}")
	void deleteProfile(@PathVariable ("userId") String userId) {
		profileService.deleteProfile(userId);
	}
	
	//to update profile
	@PutMapping("/updateProfile")
	void updateProfile(@RequestBody UserProfile user) {
		profileService.updateProfile(user);
	}
	
	//to get all the profiles
	@GetMapping("/getProfiles")
	List<UserProfile> getAllProfiles() {
		return profileService.getAllProfiles();
	}
	
	//to get profile by user id
	@GetMapping("/getProfile/{userId}")
	UserProfile getByProfileID(@PathVariable("userId") String userId) {
		return profileService.getByProfile(userId);
	}
	
	//get profile by mobile number
	@GetMapping("/getPhoneNumber/{mobilenumber}")
	UserProfile getByPhoneNumber(@PathVariable ("mobilenumber") Long mobilenumber) {
		return profileService.findByMobileNo(mobilenumber);
	}

	//get profile using username
	@GetMapping("/getprofile/{userName}")
	UserProfile getByUserName(@PathVariable("userName") String userName) {
		return profileService.getByuserName(userName);
	}
}
