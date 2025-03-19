package com.GNFGroup.Employee_Feedback_Management_Solution.services.admin;

import com.GNFGroup.Employee_Feedback_Management_Solution.dto.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getUsers();
}
