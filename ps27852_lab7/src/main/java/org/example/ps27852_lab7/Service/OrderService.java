package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<Order> getAllOrders(int page, int size); // <1>

    Order getOrderById(long id);

    Order createOrder(Order order);

    void deleteOrderById(long id);
}
