package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripEntity {

    private Long id;
    private String route;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer nDives;
    private Double price;
    private CruiseEntity cruise;
    private DestinationEntity destination;
}
