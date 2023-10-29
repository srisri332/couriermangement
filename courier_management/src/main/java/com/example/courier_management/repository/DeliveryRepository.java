package com.example.courier_management.repository;

import com.example.courier_management.entity.Delivery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {

    @Query(value = "select d from Delivery d where SenderID=:senderID and Status=:type")
    public List<Delivery> getUserCurrentDeliveries(@Param("senderID") int senderID, @Param("type") String type);

    @Query(value = "select d from Delivery d where Status=:type")
    public List<Delivery> getDeliveriesAsAdmin(@Param("type") String type);

    @Query(value = "select d from Delivery d where DeliveryID= :deliveryID ")
    public Delivery getReceiver(@Param("deliveryID") String deliveryID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Delivery SET Status= :type WHERE (DeliveryID = :deliveryID)")
    public int updateDeliveryStatus(@Param("deliveryID") String deliveryID, @Param("type") String type);


}
