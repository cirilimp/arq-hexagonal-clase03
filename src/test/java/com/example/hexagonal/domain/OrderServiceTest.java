package com.example.hexagonal.domain;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.application.service.OrderService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Pruebas unitarias y de integración
@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService orderService;


    @Test
    void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order.getId());
    }


    @Test
    void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.getOrderById(order.getId()).getStatus());
    }

}