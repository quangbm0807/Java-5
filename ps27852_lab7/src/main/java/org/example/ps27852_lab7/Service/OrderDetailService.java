package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.OrderDetail;

public interface OrderDetailService {

    void deleteOrderDetailById(long id);

    void createOrderDetail(OrderDetail orderDetail);
}
