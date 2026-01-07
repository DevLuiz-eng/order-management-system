package com.luizdev.order_management_system.DTO.response;

import com.luizdev.order_management_system.domain.Product;
import jakarta.validation.constraints.NotNull;

public record OrderItemResponseDTO(Long id, Integer quantity, @NotNull Product product, Long orderId) {
}
