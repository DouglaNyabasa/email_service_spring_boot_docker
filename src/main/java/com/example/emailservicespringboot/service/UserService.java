package com.example.emailservicespringboot.service;//package com.example.emailservice.service;


import com.example.emailservicespringboot.model.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);

}
