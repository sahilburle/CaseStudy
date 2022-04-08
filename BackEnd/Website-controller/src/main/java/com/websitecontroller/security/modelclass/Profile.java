package com.websitecontroller.security.modelclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {

    private String profileId;
    private String fullName;
    private String emailId;
    private long mobilenumber;
    private String role;
    private String password;


}
