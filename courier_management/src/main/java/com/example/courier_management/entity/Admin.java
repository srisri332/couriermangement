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
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private short AdminID;
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
