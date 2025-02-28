package com.simpleblog.hello_blog.service;

import com.simpleblog.hello_blog.model.User;
import com.simpleblog.hello_blog.model.UserPrincipal;
import com.simpleblog.hello_blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(@Lazy AuthenticationManager authenticationManager) { // Use Lazy Injection
        this.authenticationManager = authenticationManager;
    }

    public User registerUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null)
        {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(user);
    }

    public String verify(User user)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
        ));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "failed";
    }
}
