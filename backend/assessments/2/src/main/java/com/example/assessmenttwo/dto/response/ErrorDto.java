package com.example.assessmenttwo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A DTO class to send error message as response
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
 class ErrorDTO {

    @NotBlank(message = "Message should not be blank")
    String message;

    @NotNull
    int httpStatus;
}