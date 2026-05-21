package com.example.demo.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderitemRequest {
    @NotNull(message= "Product id is required")
    private Long productId;

    @NotNull(message= "Quantity id is required")
    @Min(value=1, message = "Quantity must be greater than 1")
    private Integer quantity;
}
