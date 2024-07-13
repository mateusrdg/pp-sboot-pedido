package com.pratopronto.dominio.portas.interfaces;

import com.pratopronto.dominio.Order;

public interface PaymentServicePort {

    void sendPayment(Order order);

}
