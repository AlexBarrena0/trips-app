package edu.uoc.abarrena.trips.infrastructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CruiseDto {

    private Long id;
    private String name;
    private String description;
    private int capacity;
    private float avgShipRating;
    private float avgRoomRating;
    private float avgCrewRating;
    private float avgFoodRating;
}
