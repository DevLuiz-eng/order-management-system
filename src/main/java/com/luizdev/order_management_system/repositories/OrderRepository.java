package com.luizdev.order_management_system.repositories;

import com.luizdev.order_management_system.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
