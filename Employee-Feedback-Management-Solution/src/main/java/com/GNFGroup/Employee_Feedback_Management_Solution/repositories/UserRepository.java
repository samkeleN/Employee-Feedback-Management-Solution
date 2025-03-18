package com.GNFGroup.Employee_Feedback_Management_Solution.repositories;

import com.GNFGroup.Employee_Feedback_Management_Solution.entities.User;
import com.GNFGroup.Employee_Feedback_Management_Solution.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole userRole);
}
