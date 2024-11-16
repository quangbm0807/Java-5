package org.example.ps27852_lab7.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.OrderRepository;
import org.example.ps27852_lab7.Service.OrderDetailService;
import org.example.ps27852_lab7.Service.OrderService;
import org.example.ps27852_lab7.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;


    @Override
    public Page<Order> getAllOrders(int page, int size) {
        return null;
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }
}
