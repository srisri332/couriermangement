package com.example.courier_management.controller;

import com.example.courier_management.entity.Admin;
import com.example.courier_management.entity.LoginUser;
import com.example.courier_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/loginadmin")
    public Admin loginSender(@RequestBody LoginUser user) {
        return adminService.loginAdmin(user);
    }

}
