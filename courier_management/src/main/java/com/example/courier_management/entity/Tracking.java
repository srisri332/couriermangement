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
@Table(name="tracking")
public class Tracking {
    @Id
    @Getter
    @Setter
    private String TrackingID= UUID.randomUUID().toString().substring(0, 10);
    @Getter
    @Setter
    private String Status;
    @Getter
    @Setter
    private String Location;
    @Getter
    @Setter
    private LocalDateTime DateAndTime;
    @Getter
    @Setter
    private String DeliveryID;
}
