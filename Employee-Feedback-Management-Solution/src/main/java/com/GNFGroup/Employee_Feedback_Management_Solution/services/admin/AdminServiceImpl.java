package com.GNFGroup.Employee_Feedback_Management_Solution.services.admin;

import com.GNFGroup.Employee_Feedback_Management_Solution.dto.TaskDTO;
import com.GNFGroup.Employee_Feedback_Management_Solution.dto.UserDto;
import com.GNFGroup.Employee_Feedback_Management_Solution.entities.Task;
import com.GNFGroup.Employee_Feedback_Management_Solution.entities.User;
import com.GNFGroup.Employee_Feedback_Management_Solution.enums.TaskStatus;
import com.GNFGroup.Employee_Feedback_Management_Solution.enums.UserRole;
import com.GNFGroup.Employee_Feedback_Management_Solution.repositories.TaskRepository;
import com.GNFGroup.Employee_Feedback_Management_Solution.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    private final TaskRepository taskRepository;


    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                .map(User::getUserDto).collect(Collectors.toList());
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Optional<User> optionalUser = userRepository.findById(taskDTO.getEmployeeId());
        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(TaskStatus.INPROGRESS);
            task.setUser(optionalUser.get());
            return taskRepository.save(task).getTaskDTO();
        }
        return null;
    }
}
