package com.spa.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spa.models.Role;
import com.spa.models.User;
import com.spa.repositories.RoleRepository;
import com.spa.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findAllByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setRoles(roleRepository.findAllByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    public void saveWithCustomerRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findAllByName("ROLE_CUSTOMER"));
        userRepository.save(user);
    }
    
    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}