package com.GNFGroup.Employee_Feedback_Management_Solution.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private String userRole;
}
