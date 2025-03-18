package com.GNFGroup.Employee_Feedback_Management_Solution.dto;

import com.GNFGroup.Employee_Feedback_Management_Solution.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;

}
