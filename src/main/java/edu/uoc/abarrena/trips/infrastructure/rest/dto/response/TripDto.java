package edu.uoc.abarrena.trips.infrastructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private String route;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer nDives;
    private Double price;
    private CruiseDto cruise;
    private DestinationDto destination;
}