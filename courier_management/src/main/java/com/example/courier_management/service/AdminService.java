package com.example.courier_management.service;

import com.example.courier_management.entity.Admin;
import com.example.courier_management.entity.LoginUser;
import com.example.courier_management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin loginAdmin(LoginUser user) {
        for (Admin admin : adminRepository.findAll()) {
            if (admin.getEmail().equalsIgnoreCase(user.getEmail())
                    && admin.getPassword().equals(user.getPassword())) {
                admin.setPassword("********");
                return admin;
            }
        }
        return null;
    }
}
