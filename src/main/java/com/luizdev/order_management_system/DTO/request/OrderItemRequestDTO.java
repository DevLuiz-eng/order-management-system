package com.luizdev.order_management_system.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO(@NotNull @Min(1) Integer quantity, @NotNull Long productId) {
}
