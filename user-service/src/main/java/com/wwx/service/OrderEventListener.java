package com.wwx.service;

import com.wwx.dto.OrderDTO;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "user.order.queue", durable = "true"),
        exchange = @Exchange(value = "order.event.exchange", type = "topic"),
        key = "order.*"
    ))
    public void handleOrderEvent(OrderDTO order) {
        // 处理订单事件
        System.out.println(order.getOrderId());
    }

    /**
     * 两个 @RabbitListener 方法监听 同一个队列（user.order.queue），RabbitMQ 会：
     * 轮询分发消息（Round-Robin）给不同的消费者。
     * 不会重复消费同一条消息（即一条消息只会被一个监听器处理）。
     * @param order
     * @param routingKey
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "user.order.queue", durable = "true"),
            exchange = @Exchange(value = "order.event.exchange", type = "topic"),
            key = "order.cancel"
    ))
    public void handleOrderCancel(OrderDTO order, @Header("amqp_receivedRoutingKey") String routingKey, Message message) {
        // 处理订单取消事件
        String routingKey2 = message.getMessageProperties().getReceivedRoutingKey();
    }
}