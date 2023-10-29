package com.example.courier_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "admin_manager")
public class AdminManager {
    @Getter
    @Setter
    private int SenderID;
    @Getter
    @Setter
    private String PkdID;
    @Getter
    @Setter
    private short AdminID;
    @Getter
    @Setter
    @Id
    private String DeliveryID;
}
