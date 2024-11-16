package org.example.ps27852_lab7.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.OrderDetailRepository;
import org.example.ps27852_lab7.Service.OrderDetailService;
import org.example.ps27852_lab7.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public void deleteOrderDetailById(long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
