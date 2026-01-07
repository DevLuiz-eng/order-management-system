package com.luizdev.order_management_system.DTO.response;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id, String name, BigDecimal price, Integer stock) {
}
