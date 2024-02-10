package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.DeviceInventoryEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents a response that contains a list of inventory items.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInventoryResponse {

    /**
     * A list of inventory items.
     */
    @NotBlank(message = "Inventory list cannot be empty")
    private List<DeviceInventoryEntity> inventoryEntityList;

    /**
     * HTTP status indicating the result of the list inventory request.
     */
    @NotBlank(message = "HTTP status must be provided")
    private HttpStatus httpStatus;
}
