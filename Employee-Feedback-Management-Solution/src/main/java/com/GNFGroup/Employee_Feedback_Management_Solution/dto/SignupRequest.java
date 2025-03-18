package com.GNFGroup.Employee_Feedback_Management_Solution.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}
