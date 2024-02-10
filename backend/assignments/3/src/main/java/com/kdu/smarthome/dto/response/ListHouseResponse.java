package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.HouseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents a response containing a list of houses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListHouseResponse {

    /**
     * Message indicating the outcome of the request to list houses.
     */
    @NotBlank(message = "Response message cannot be empty")
    private String message;

    /**
     * A list of all the  houses.
     */
    @NotBlank(message = "House list cannot be empty")
    private List<HouseEntity> housesList;

    /**
     * HTTP status indicating the result of the list houses request.
     */
    @NotBlank(message = "HTTP status must be provided")
    private HttpStatus httpStatus;
}
