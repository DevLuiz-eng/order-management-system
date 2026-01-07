package com.luizdev.order_management_system.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@NotNull String name, @NotNull @Min(1) Integer age) {
}
