package com.example.courier_management.service;

import com.example.courier_management.entity.LoginUser;
import com.example.courier_management.entity.Sender;
import com.example.courier_management.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenderService {

    @Autowired
    private SenderRepository senderRepository;

    public Sender saveSender(Sender sender){
        return senderRepository.save(sender);
    }

    public Sender loginSender(LoginUser user){
        for(Sender sender: senderRepository.findAll()){
            if(sender.getEmail().equalsIgnoreCase(user.getEmail())
                    && sender.getPassword().equals(user.getPassword())){
                sender.setPassword("********");
                return sender;
            }
        }
        return null;
    }

    public Sender getSenderById(int id){
        return senderRepository.findById(id).orElse(null);
    }
}
