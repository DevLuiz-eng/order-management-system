package com.luizdev.order_management_system.DTO.response;

import com.luizdev.order_management_system.domain.OrderItem;
import com.luizdev.order_management_system.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderResponseDTO(Long id,
                               List<OrderItem> orderItem,
                               Long userId,
                               @NotNull OrderStatus orderStatus,
                               String description) {
}
