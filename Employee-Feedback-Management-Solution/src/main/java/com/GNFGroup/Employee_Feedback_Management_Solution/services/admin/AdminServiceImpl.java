package com.GNFGroup.Employee_Feedback_Management_Solution.services.admin;

import com.GNFGroup.Employee_Feedback_Management_Solution.dto.UserDto;
import com.GNFGroup.Employee_Feedback_Management_Solution.entities.User;
import com.GNFGroup.Employee_Feedback_Management_Solution.enums.UserRole;
import com.GNFGroup.Employee_Feedback_Management_Solution.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                .map(User::getUserDto).collect(Collectors.toList());
    }
}
