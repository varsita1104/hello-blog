package com.simpleblog.hello_blog.servicetest;

import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.repo.UserRepo;
import com.simpleblog.hello_blog.service.JWTService;
import com.simpleblog.hello_blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private JWTService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp()
    {
        mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("test123");
    }

    @Test
    void testUserByUsername_success()
    {
        when(userRepo.findByUsername("testUser")).thenReturn(mockUser);

        UserDetails userDetails = userService.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    void testUserByUsername_userNotFoundException()
    {
        when(userRepo.findByUsername("unknown")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("unknown"));
    }

}
