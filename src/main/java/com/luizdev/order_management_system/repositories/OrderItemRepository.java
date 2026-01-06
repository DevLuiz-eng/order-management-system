package com.luizdev.order_management_system.repositories;

import com.luizdev.order_management_system.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Long, OrderItem> {
}
