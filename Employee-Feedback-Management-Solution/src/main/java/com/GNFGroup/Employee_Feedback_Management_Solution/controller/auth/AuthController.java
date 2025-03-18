package com.GNFGroup.Employee_Feedback_Management_Solution.controller.auth;

import com.GNFGroup.Employee_Feedback_Management_Solution.dto.AuthenticationRequest;
import com.GNFGroup.Employee_Feedback_Management_Solution.dto.AuthenticationResponse;
import com.GNFGroup.Employee_Feedback_Management_Solution.dto.SignupRequest;
import com.GNFGroup.Employee_Feedback_Management_Solution.dto.UserDto;
import com.GNFGroup.Employee_Feedback_Management_Solution.entities.User;
import com.GNFGroup.Employee_Feedback_Management_Solution.repositories.UserRepository;
import com.GNFGroup.Employee_Feedback_Management_Solution.services.auth.AuthService;
import com.GNFGroup.Employee_Feedback_Management_Solution.services.jwt.UserService;
import com.GNFGroup.Employee_Feedback_Management_Solution.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if(authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exists");
        }
        UserDto createdUserDto = authService.signupUser(signupRequest);
        if(createdUserDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(String.valueOf(optionalUser.get().getUserRole()));
        }
        return authenticationResponse;
    }
}
