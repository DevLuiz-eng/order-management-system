package com.luizdev.order_management_system.DTO.request;

import com.luizdev.order_management_system.domain.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDTO(@NotEmpty List<OrderItem> items, @NotNull Long userId, @NotNull String description) {
}
