package com.example.courier_management.service;


import com.example.courier_management.entity.*;
import com.example.courier_management.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ReceiverRepository receiverRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    private SenderRepository senderRepository;
    @Autowired
    private AdminManagerRepository adminManagerRepository;

    public String processOrder(OrderPackage pkg, Sender sender, Receiver receiver, Payment payment) {
        //Update package in package table
        packageRepository.save(pkg);

        //Update delivery in delivery table
        Delivery delivery = new Delivery();
        delivery.setSenderID(sender.getSenderID());
        delivery.setReceiverID(receiver.getReceiverID());
        delivery.setPkdID(pkg.getPkdID());
        delivery.setStatus("In Progress");
        deliveryRepository.save(delivery);

        //Update tracking in tracking table
        Tracking tracking = new Tracking();
        tracking.setStatus("In Progress");
        tracking.setLocation("Warehouse");
        tracking.setDeliveryID(delivery.getDeliveryID());
        tracking.setDateAndTime(delivery.getDateAndTime());
        trackingRepository.save(tracking);

        //Update payment in payment table
        payment.setPkdID(pkg.getPkdID());
        payment.setDateAndTime(LocalDateTime.now());
        paymentRepository.save(payment);

        //Update details in admin_manager table
        AdminManager adminManager = new AdminManager();
        adminManager.setAdminID((short) 123);
        adminManager.setSenderID(sender.getSenderID());
        adminManager.setDeliveryID(delivery.getDeliveryID());
        adminManager.setPkdID(pkg.getPkdID());
        adminManagerRepository.save(adminManager);

        return "Success";
    }

    public List<Delivery> getSenderCurrentOrders(int senderID, String type) {
        return deliveryRepository.getUserCurrentDeliveries(senderID, type);
    }

    public List<Delivery> getCurrentDeliveriesAsAdmin() {
        return deliveryRepository.getDeliveriesAsAdmin("In Progress");
    }

    public List<Delivery> getCompletedDeliveriesAsAdmin() {
        return deliveryRepository.getDeliveriesAsAdmin("Completed");
    }

    public List<Delivery> getOutForDeliveriesAsAdmin() {
        return deliveryRepository.getDeliveriesAsAdmin("Out For Delivery");
    }


    public int updateDeliveryStatus(String type, String deliveryID) {
        int deliveryResult = deliveryRepository.updateDeliveryStatus(deliveryID, type);
        int trackingResult = trackingRepository.updateTrackingStatus(deliveryID, type);
        if (type.equalsIgnoreCase("completed")) {
            int receiverID = deliveryRepository.getReceiver(deliveryID).getReceiverID();
            Optional<Receiver> r = receiverRepository.findById(receiverID);
            trackingRepository.updateLocation(deliveryID, r.get().getAddress());
        } else {
            trackingRepository.updateLocation(deliveryID, randomLocation());
        }

        return deliveryResult & trackingResult;
    }

    public Optional<OrderPackage> getPackageDetails(String PkdID) {
        return packageRepository.findById(PkdID);
    }

    public Optional<Receiver> getReceiverDetails(int receiverID) {
        return receiverRepository.findById(receiverID);
    }

    public Optional<Tracking> getTrackingDetails(String trackingID) {
        return trackingRepository.findById(trackingID);
    }

    private String randomLocation() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);
        return randomString;
    }
}
