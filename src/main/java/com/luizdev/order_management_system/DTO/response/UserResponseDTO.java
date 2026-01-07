package com.luizdev.order_management_system.DTO.response;

import com.luizdev.order_management_system.domain.Order;

import java.util.List;

public record UserResponseDTO(Long id, String name, Integer age, List<Order> orders) {
}
