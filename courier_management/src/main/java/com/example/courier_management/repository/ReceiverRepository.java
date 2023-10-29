package com.example.courier_management.repository;

import com.example.courier_management.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverRepository extends JpaRepository<Receiver,Integer> {
}
