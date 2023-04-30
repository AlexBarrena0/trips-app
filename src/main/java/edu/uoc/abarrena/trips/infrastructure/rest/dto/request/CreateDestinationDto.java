package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDestinationDto {

    @NotBlank(message = "Destination description is required")
    @Max(value = 255, message = "Destination description must be less than 255 characters")
    private String description;
}
