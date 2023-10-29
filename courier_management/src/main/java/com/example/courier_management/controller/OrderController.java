package com.example.courier_management.controller;

import com.example.courier_management.entity.*;
import com.example.courier_management.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/processorder")
    public String processOrder(@RequestBody String order) throws JsonProcessingException {
        JSONObject orderJson = new JSONObject(order);
        ObjectMapper mapper = new ObjectMapper();
        OrderPackage orderPackage = mapper.readValue(orderJson.getJSONObject("orderPackage").toString(), OrderPackage.class);
        Sender sender = mapper.readValue(orderJson.getJSONObject("sender").toString(), Sender.class);
        Receiver receiver = mapper.readValue(orderJson.getJSONObject("receiver").toString(), Receiver.class);
        Payment payment = mapper.readValue(orderJson.getJSONObject("payment").toString(), Payment.class);
        return orderService.processOrder(orderPackage, sender, receiver, payment);
    }

    @PostMapping(path = "/currentorders", produces = MediaType.APPLICATION_JSON_VALUE)
    public String senderCurrentOrders(@RequestBody String request) throws JsonProcessingException {
        JSONObject requestJson = new JSONObject(request);
        ObjectMapper mapper = new ObjectMapper();
        Sender sender = mapper.readValue(requestJson.getJSONObject("sender").toString(), Sender.class);

        JSONObject responseJson = new JSONObject();
        List<Delivery> deliveries = orderService.getSenderCurrentOrders(sender.getSenderID(), "In Progress");
        for (Delivery d : deliveries) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        List<Delivery> deliveriesOut = orderService.getSenderCurrentOrders(sender.getSenderID(), "Out For Delivery");
        for (Delivery d : deliveriesOut) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        return responseJson.toString();

    }

    @PostMapping(path = "/completeorders", produces = MediaType.APPLICATION_JSON_VALUE)
    public String senderCompleteOrders(@RequestBody String request) throws JsonProcessingException {
        JSONObject requestJson = new JSONObject(request);
        ObjectMapper mapper = new ObjectMapper();
        Sender sender = mapper.readValue(requestJson.getJSONObject("sender").toString(), Sender.class);

        JSONObject responseJson = new JSONObject();
        List<Delivery> deliveries = orderService.getSenderCurrentOrders(sender.getSenderID(), "Completed");
        for (Delivery d : deliveries) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        return responseJson.toString();

    }

    @GetMapping(path = "/admin/current-deliveries", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAdminCurrentDeliveries() {

        JSONObject responseJson = new JSONObject();
        List<Delivery> deliveries = orderService.getCurrentDeliveriesAsAdmin();
        for (Delivery d : deliveries) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        return responseJson.toString();

    }

    @GetMapping(path = "/admin/completed-deliveries", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAdminCompletedDeliveries() {

        JSONObject responseJson = new JSONObject();
        List<Delivery> deliveries = orderService.getCompletedDeliveriesAsAdmin();
        for (Delivery d : deliveries) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        return responseJson.toString();
    }

    @GetMapping(path = "/admin/out-for-deliveries", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAdminOutForDeliveries() {

        JSONObject responseJson = new JSONObject();
        List<Delivery> deliveries = orderService.getOutForDeliveriesAsAdmin();
        for (Delivery d : deliveries) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("deliveryID", d.getDeliveryID());
            tempObject.put("deliveryStatus", d.getStatus());

            Optional<OrderPackage> p = orderService.getPackageDetails(d.getPkdID());
            tempObject.put("pkgDescription", p.get().getDescription());
            tempObject.put("pkgWeight", p.get().getWeight());
            tempObject.put("pkgFragile", p.get().isIsFragile());

            Optional<Receiver> r = orderService.getReceiverDetails(d.getReceiverID());
            tempObject.put("receiverName", r.get().getName());
            tempObject.put("receiverEmail", r.get().getEmail());
            tempObject.put("receiverAdd", r.get().getAddress());
            tempObject.put("receiverPhn", r.get().getPhone());
            responseJson.append("details", tempObject);
        }

        return responseJson.toString();
    }

    @GetMapping("/admin/outfordelivery/{deliveryID}")
    public int updateDeliveryStatus(@PathVariable String deliveryID) {
        return orderService.updateDeliveryStatus("Out For Delivery", deliveryID);
    }

    @GetMapping("/admin/completeddelivery/{deliveryID}")
    public int updateDeliveryComplete(@PathVariable String deliveryID) {
        return orderService.updateDeliveryStatus("Completed", deliveryID);
    }

    @GetMapping("/tracking/{trackingID}")
    public Optional<Tracking> getTrackingDetails(@PathVariable String trackingID) {
        return orderService.getTrackingDetails(trackingID);
    }

}
