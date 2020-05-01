package org.mddarr.orders.service;

import org.mddarr.orders.Constants;
import org.mddarr.orders.beans.OrderDTO;


import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.event.dto.OrderState;
import org.mddarr.orders.port.OrderServicePublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderAvroProducer implements OrderServicePublish {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplateOrder;
    private static final Logger logger = LoggerFactory.getLogger(OrderAvroProducer.class);

    @Override
    public void sendOrder(Order order) {
        logger.info("Send order  {}", order);
        kafkaTemplateOrder.send("orders", order.getId(), order);
    }
}
