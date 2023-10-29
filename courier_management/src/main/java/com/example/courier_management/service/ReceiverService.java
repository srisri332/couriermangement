package com.example.courier_management.service;

import com.example.courier_management.entity.Receiver;
import com.example.courier_management.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;

    public Receiver saveReceiver(Receiver receiver) {
        return receiverRepository.save(receiver);
    }

    public List<Receiver> getReceiversForSender(int SenderID) {
        List<Receiver> result = new ArrayList<>();
        for (Receiver r : receiverRepository.findAll()) {
            if (r.getSenderID() == SenderID)
                result.add(r);
        }
        return result;
    }

}
