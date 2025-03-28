package com.GNFGroup.Employee_Feedback_Management_Solution.repositories;


import com.GNFGroup.Employee_Feedback_Management_Solution.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
