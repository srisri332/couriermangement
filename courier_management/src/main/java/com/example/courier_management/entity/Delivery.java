package com.example.courier_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @Getter
    @Setter
    private String DeliveryID= UUID.randomUUID().toString().substring(0, 10);
    @Getter
    @Setter
    private String Status;
    @Getter
    @Setter
    private LocalDateTime DateAndTime =  LocalDateTime.now().plusDays(7);
    @Getter
    @Setter
    private int SenderID;
    @Getter
    @Setter
    private String PkdID;
    @Getter
    @Setter
    private int ReceiverID;
}
