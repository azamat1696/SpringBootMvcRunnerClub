package org.payment.web.service.impl;

import org.jetbrains.annotations.NotNull;
import org.payment.web.dto.UserRegistrationDto;
import org.payment.web.models.Role;
import org.payment.web.models.UserEntity;
import org.payment.web.repository.RoleRepository;
import org.payment.web.repository.UserRepository;
import org.payment.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(@NotNull UserRegistrationDto registrationDto) {
       UserEntity userEntity = new UserEntity();
       userEntity.setUsername(registrationDto.getUsername());
       userEntity.setEmail(registrationDto.getEmail());
       userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
       Role role = roleRepository.findByName("USER");
       userEntity.setRoles(Arrays.asList(role));
       userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return null;
    }
}
