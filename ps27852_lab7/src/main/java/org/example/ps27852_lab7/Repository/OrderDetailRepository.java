package org.example.ps27852_lab7.Repository;

import org.example.ps27852_lab7.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
