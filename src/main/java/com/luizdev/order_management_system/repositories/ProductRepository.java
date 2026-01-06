package com.luizdev.order_management_system.repositories;

import com.luizdev.order_management_system.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, Product> {
}
