package com.example.courier_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name="package")
public class OrderPackage {
    @Id
    @Getter
    @Setter
    private String PkdID=UUID.randomUUID().toString().substring(0, 10);
    @Getter
    @Setter
    private String Description;
    @Getter
    @Setter
    private boolean IsFragile;
    @Getter
    @Setter
    private String Weight;
}
