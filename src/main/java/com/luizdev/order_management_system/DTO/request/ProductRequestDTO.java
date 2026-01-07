package com.luizdev.order_management_system.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotNull String name, @NotNull @Min(0) BigDecimal price, @NotNull @Min(0) Integer stock){
}
