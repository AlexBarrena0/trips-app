package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindTripsFiltersDto {

    private Long cruiseId;
    private Long destinationId;
    private LocalDate startDate;
    private LocalDate endDate;
}
