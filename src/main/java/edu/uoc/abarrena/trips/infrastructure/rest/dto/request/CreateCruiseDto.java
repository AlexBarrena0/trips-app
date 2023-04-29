package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCruiseDto {

    @NotBlank(message = "Cruise name is required")
    private String name;
    @NotBlank(message = "Cruise description is required")
    private String description;
}
