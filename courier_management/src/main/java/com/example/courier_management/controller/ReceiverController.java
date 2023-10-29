package com.example.courier_management.controller;

import com.example.courier_management.entity.Receiver;
import com.example.courier_management.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReceiverController {

    @Autowired
    private ReceiverService receiverService;

    @PostMapping("/addreceiver")
    public Receiver addSender(@RequestBody Receiver receiver) {
        return receiverService.saveReceiver(receiver);
    }

    @GetMapping("/getreceivers/{senderID}")
    public List<Receiver> getReceivers(@PathVariable int senderID) {
        return receiverService.getReceiversForSender(senderID);
    }

}
