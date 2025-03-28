package com.GNFGroup.Employee_Feedback_Management_Solution.dto;

import com.GNFGroup.Employee_Feedback_Management_Solution.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private TaskStatus taskStatus;

    private Long employeeId;

    private String employeeName;

}
