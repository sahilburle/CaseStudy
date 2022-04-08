package com.websitecontroller.controller;

import com.websitecontroller.model.AuthenticationRequest;
import com.websitecontroller.model.AuthenticationResponse;
import com.websitecontroller.security.service.DetailsUserService;
import com.websitecontroller.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.websitecontroller.service.service;

@RestController
@CrossOrigin
@RequestMapping("main")
public class controller {

	@Autowired
	service service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private DetailsUserService detailsUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping("/allProfiles")
	public ResponseEntity<?> profiles()
	{
		return ResponseEntity.ok(service.viewProfiles().getBody());
	}

	@GetMapping("/allProducts")
	public ResponseEntity<?> products()
	{
		return service.viewProducts();
	}

	//Authentication and generating JWT token
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			System.out.println("`before` authenticate");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(),
							authenticationRequest.getPassword()
					)
			);
			System.out.println("after authenticate");
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or password", e);
		}

		final UserDetails userDetails = detailsUserService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println("loaduser " + userDetails.getUsername() + " " + userDetails.getPassword() + " " + userDetails.getAuthorities());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getAuthorities().toString(), userDetails));
	}
}