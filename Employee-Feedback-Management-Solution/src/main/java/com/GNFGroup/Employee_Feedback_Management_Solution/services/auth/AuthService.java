package com.GNFGroup.Employee_Feedback_Management_Solution.services.auth;

import com.GNFGroup.Employee_Feedback_Management_Solution.dto.SignupRequest;
import com.GNFGroup.Employee_Feedback_Management_Solution.dto.UserDto;

public interface AuthService {

    UserDto signupUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
