package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.RoomEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomResponse {

    @NotBlank(message = "Please provide a response message")
    private String message;

    @NotBlank(message = "Room information is required")
    private RoomEntity room;

    @NotBlank(message = "HTTP status is missing")
    private HttpStatus httpStatus;
}
