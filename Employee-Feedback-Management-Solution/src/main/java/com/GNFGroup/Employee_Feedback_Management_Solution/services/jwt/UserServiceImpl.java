package com.GNFGroup.Employee_Feedback_Management_Solution.services.jwt;

import com.GNFGroup.Employee_Feedback_Management_Solution.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
                return  userRepository.findFirstByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }
}
