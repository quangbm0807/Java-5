package org.example.ps27852_lab7.Repository;

import org.example.ps27852_lab7.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
