package com.wwx.controller;

import com.wwx.dto.OrderDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @PostMapping("/orders")
    public String createOrder() {
        // 业务逻辑...
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("test123");
        orderDTO.setCustomerId("test456");
        rabbitTemplate.convertAndSend("order.event.exchange", "order.create", orderDTO);
        return "Order created";
    }
}