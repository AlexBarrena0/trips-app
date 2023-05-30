package edu.uoc.abarrena.trips.application.dto.request;

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
    private Long companyId;
    private Long travelerId;
}
