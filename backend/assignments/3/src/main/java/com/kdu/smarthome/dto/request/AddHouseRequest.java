package com.kdu.smarthome.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to add a new house with specific details.
 * This class captures the necessary information required for adding a house,
 * such as its address and name, and ensures that the provided information is valid.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHouseRequest {

    @NotBlank(message = "Address field should not be empty")
    @Size(min = 3, max = 100, message = "Address field must be between 3 and 100 characters")
    private String address;

    @NotBlank(message = "House name field should not be empty")
    @Size(min = 3, max = 100, message = "House name field must be between 3 and 100 characters")
    private String houseName;

}
