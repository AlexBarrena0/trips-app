package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingDto {

    @NotBlank(message = "Trip id is required")
    Long tripId;

    @NotBlank(message = "Traveler id is required")
    Long travelerId;
}
