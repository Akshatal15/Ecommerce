package app.ecom.dto.request_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {

    @NotNull(message = "Product ID is required")
    private int productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}