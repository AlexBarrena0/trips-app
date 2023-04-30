package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cruise {

    private Long id;
    private String name;
    private String description;
    private int capacity;
    private float avgShipRating;
    private float avgRoomRating;
    private float avgCrewRating;
    private float avgFoodRating;

    public Cruise(Long id, String name, String description, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }

}
