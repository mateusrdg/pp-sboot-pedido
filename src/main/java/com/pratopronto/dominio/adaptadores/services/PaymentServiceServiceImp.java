package com.pratopronto.dominio.adaptadores.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.dtos.order.UpdateOrderDTO;
import com.pratopronto.dominio.enums.StatusEnum;
import com.pratopronto.dominio.portas.interfaces.OrderServicePort;
import com.pratopronto.dominio.portas.interfaces.PaymentServicePort;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;

public class PaymentServiceServiceImp implements PaymentServicePort {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final OrderRepositoryPort orderRepositoryPort;
    private final ObjectMapper objectMapper;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    public PaymentServiceServiceImp(QueueMessagingTemplate queueMessagingTemplate, OrderRepositoryPort orderRepositoryPort, ObjectMapper objectMapper) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.orderRepositoryPort = orderRepositoryPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendPayment(Order order) {
        try {
            queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(objectMapper.writeValueAsString(order)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SqsListener("status-pedido")
    public void loadMessageFromSQS(String message) {
        Order order;
        try {
            order = objectMapper.readValue(message, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(StatusEnum.PRONTO);
        order.setStatus(StatusEnum.PRONTO);
        orderRepositoryPort.update(order);;
    }
}
