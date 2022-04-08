package com.websitecontroller.security.modelclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String userName;
    private String userPass;
    private boolean value;
    private String role;
    private String profileId;
}
