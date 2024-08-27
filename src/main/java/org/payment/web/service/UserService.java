package org.payment.web.service;

import org.payment.web.dto.UserRegistrationDto;
import org.payment.web.models.UserEntity;

public interface UserService {
    void saveUser(UserRegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
