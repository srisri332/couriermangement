package com.example.courier_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @Getter
    @Setter
    private String PaymentID= UUID.randomUUID().toString().substring(0, 10);
    @Getter
    @Setter
    private String Type;
    @Getter
    @Setter
    private LocalDateTime DateAndTime;
    @Getter
    @Setter
    private Float Amount;
    @Getter
    @Setter
    private String PkdID;
    @Getter
    @Setter
    private int SenderID;
}
