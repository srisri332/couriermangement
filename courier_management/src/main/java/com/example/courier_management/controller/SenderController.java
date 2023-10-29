package com.example.courier_management.controller;

import com.example.courier_management.entity.LoginUser;
import com.example.courier_management.entity.Sender;
import com.example.courier_management.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SenderController {

    @Autowired
    private SenderService senderService;

    @PostMapping("/addsender")
    public Sender addSender(@RequestBody Sender sender){
        return senderService.saveSender(sender);
    }

    @PostMapping("/loginsender")
    public Sender loginSender(@RequestBody LoginUser user){
        return senderService.loginSender(user);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
