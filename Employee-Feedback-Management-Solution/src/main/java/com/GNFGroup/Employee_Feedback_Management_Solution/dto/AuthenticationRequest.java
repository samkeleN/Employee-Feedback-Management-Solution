package com.GNFGroup.Employee_Feedback_Management_Solution.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
