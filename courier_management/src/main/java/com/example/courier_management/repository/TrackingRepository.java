package com.example.courier_management.repository;

import com.example.courier_management.entity.Tracking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrackingRepository extends JpaRepository<Tracking, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Tracking SET Status= :type WHERE (DeliveryID = :deliveryID)")
    public int updateTrackingStatus(@Param("deliveryID") String deliveryID, @Param("type") String type);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Tracking SET Location= :location WHERE (DeliveryID = :deliveryID)")
    public int updateLocation(@Param("deliveryID") String deliveryID, @Param("location") String location);
}
