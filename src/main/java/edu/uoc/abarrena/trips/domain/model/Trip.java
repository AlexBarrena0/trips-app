package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    private Long id;
    private String route;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer nDives;
    private Double price;
    private Integer availablePlaces;
    private Cruise cruise;
    private Destination destination;

    public Trip(Long id) {
        this.id = id;
    }
}
