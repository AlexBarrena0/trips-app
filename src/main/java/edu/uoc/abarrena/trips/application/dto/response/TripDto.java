package edu.uoc.abarrena.trips.application.dto.response;

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
    private Integer dives;
    private Double price;
    private Integer availablePlaces;
    private CruiseDto cruise;
    private DestinationDto destination;
}
