package com.example.courier_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="receiver")
public class Receiver {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int ReceiverID;
    @Getter
    @Setter
    private String Name;
    @Getter
    @Setter
    private String Email;
    @Getter
    @Setter
    private int Phone;
    @Getter
    @Setter
    private String Address;
    @Getter
    @Setter
    private int SenderID;

}
