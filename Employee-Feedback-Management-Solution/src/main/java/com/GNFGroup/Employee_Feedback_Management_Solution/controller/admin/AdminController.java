package com.GNFGroup.Employee_Feedback_Management_Solution.controller.admin;

import com.GNFGroup.Employee_Feedback_Management_Solution.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }
}
