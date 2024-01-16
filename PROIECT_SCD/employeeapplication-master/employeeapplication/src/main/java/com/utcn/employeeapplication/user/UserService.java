package com.utcn.employeeapplication.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@Slf4j

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static String hashPassword(String password) {
        // Generate a salt
        String salt = BCrypt.gensalt();
        // Encode the password with the salt
        String hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    @Transactional
    public User create(@RequestBody User user){
        log.info ("Saved a new user");

        String encPass = hashPassword(user.getPassword());
        user.setOccupation("Admin");
        user.setPassword(encPass);
        return userRepository.save(user);
    }

}
