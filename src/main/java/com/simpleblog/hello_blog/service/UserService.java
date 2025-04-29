package com.simpleblog.hello_blog.service;

import com.simpleblog.hello_blog.exception.service.BadCredentialsException;
import com.simpleblog.hello_blog.exception.service.EmailAlreadyExistsException;
import com.simpleblog.hello_blog.exception.service.EmailNotFoundException;
import com.simpleblog.hello_blog.exception.service.UserAlreadyExistsException;
import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.repo.UserRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo userRepo, JWTService jwtService, @Lazy AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService; // Use Lazy Injection
        this.authenticationManager = authenticationManager;
    }

    public void registerUser(User user)
    {
        if(userRepo.findByEmail(user.getEmailId()).isPresent())
        {
            throw new EmailAlreadyExistsException("User EmailId Already Present");
        }
        else if(userRepo.findByUsername(user.getUsername()).isPresent())
        {
            throw new UserAlreadyExistsException("Username Already Present");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("username not found"));
    }

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException
    {
        return userRepo.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("emailId not found"));
    }

    public String verify(User user, boolean isEmailLogin)
    {
        Authentication authentication = null;
        String identifier = isEmailLogin ? user.getEmailId() : user.getUsername();

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                identifier, user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        throw new BadCredentialsException("Invalid username/email or password");
    }
}
