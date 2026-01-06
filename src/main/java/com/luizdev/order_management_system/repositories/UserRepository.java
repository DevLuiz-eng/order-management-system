package com.luizdev.order_management_system.repositories;

import com.luizdev.order_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
