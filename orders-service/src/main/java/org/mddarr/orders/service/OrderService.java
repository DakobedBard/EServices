package org.mddarr.orders.service;


import org.mddarr.orders.beans.OrderDTO;
import org.mddarr.orders.dao.OrderRepository;
import org.mddarr.orders.entity.OrderEntity;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.event.dto.OrderState;
import org.mddarr.orders.port.OrderServicePublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//
@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    OrderRepository orderRepositoryy;
    private final OrderAvroProducer producer;

    @Autowired
    OrderService(OrderAvroProducer producer, OrderRepository repository) {
        this.producer = producer; this.orderRepositoryy = repository;
    }

    @Autowired
    private OrderServicePublish orderServicePublish;
//
    public OrderEntity createOrder(OrderDTO orderDTO){
        UUID uuid; // = UUID.randomUUID();
        List<Order> orders = new ArrayList<Order>();
        List<String> order_ids = new ArrayList<>();
        Order order;

        Double total = 0.0;
        for(int i = 0; i < orderDTO.getProducts().size(); i++){
            uuid = UUID.randomUUID();
            order_ids.add(uuid.toString());
            order = new Order(uuid.toString(),orderDTO.getCustomerID(), OrderState.PENDING,
                    orderDTO.getProducts().get(i), orderDTO.getQuantities().get(i), orderDTO.getPrices().get(i));
            producer.sendOrder(order);
            log.info("Order with id "+order.getId()+" sent to orders topic");
            total += orderDTO.getPrices().get(i);
        }

        uuid = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity(uuid.toString(),orderDTO.getProducts(),orderDTO.getQuantities(),
                orderDTO.getPrices(),order_ids,total, orderDTO.getCustomerID(), OrderState.PENDING.toString());
        orderRepositoryy.save(orderEntity);


        return orderEntity;
    }

    public void deleteOrder(String id){

    }




}
