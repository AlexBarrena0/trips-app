package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @NotBlank(message = "Trip id is required")
    Long tripId;

    @NotBlank(message = "User id is required")
    Long userId;
}
