package com.pratopronto.dominio.adaptadores.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.enums.StatusEnum;
import com.pratopronto.dominio.portas.repositories.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PaymentServiceServiceImpTest {

    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PaymentServiceServiceImp paymentServiceServiceImp;

    @Captor
    private ArgumentCaptor<Message<String>> messageCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(paymentServiceServiceImp, "endpoint", "https://sqs.us-east-2.amazonaws.com/211125787133/status-pedido");
    }

    @Test
    void testSendPayment() throws JsonProcessingException {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(StatusEnum.EM_PREPARACAO);

        when(objectMapper.writeValueAsString(order)).thenReturn("orderJson");

        paymentServiceServiceImp.sendPayment(order);

        verify(queueMessagingTemplate, times(1)).send(eq("https://sqs.us-east-2.amazonaws.com/211125787133/status-pedido"), messageCaptor.capture());

        Message<String> capturedMessage = messageCaptor.getValue();
        assertNotNull(capturedMessage);
        assertEquals("orderJson", capturedMessage.getPayload());
    }

    @Test
    void testLoadMessageFromSQS() throws JsonProcessingException {
        String message = "{\"id\":1,\"status\":\"EM_PREPARACAO\"}";
        Order order = new Order();
        order.setId(1L);
        order.setStatus(StatusEnum.EM_PREPARACAO);

        when(objectMapper.readValue(message, Order.class)).thenReturn(order);

        paymentServiceServiceImp.loadMessageFromSQS(message);

        verify(orderRepositoryPort, times(1)).update(order);
        assertEquals(StatusEnum.PRONTO, order.getStatus());
    }
}
