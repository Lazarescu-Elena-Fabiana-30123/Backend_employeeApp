package com.utcn.employeeapplication.security;

import com.utcn.employeeapplication.user.User;
import com.utcn.employeeapplication.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/login")
// @Slf4j

public class LoginController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/admin")
    public JwtToken adminLogin(@RequestBody Credentials credentials) throws AuthenticationException {

        User user = userRepository.findByUsername(credentials.getUsername());

        if(user != null && passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(JwtUtil.generateToken(credentials.getUsername()));
            return jwtToken;
        }

        throw new AuthenticationException("Invalid username or password");
    }
}