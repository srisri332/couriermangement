package com.example.courier_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="sender")
public class Sender {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int SenderID;
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
    private String Password;
}
