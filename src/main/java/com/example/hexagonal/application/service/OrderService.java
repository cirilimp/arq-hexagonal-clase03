package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        orderRepository.save(order);
        return order;
    }


    public List<Order> getAllOrders() {

        List<Order> nuevaLista= new ArrayList<>();

        orderRepository.findAll().forEach(nuevaLista::add);
        return nuevaLista;
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        return null;
    }

    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.updateStatus(status);
            orderRepository.save(order);
        }
    }
}
