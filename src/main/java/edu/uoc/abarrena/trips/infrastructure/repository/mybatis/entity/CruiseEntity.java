package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CruiseEntity {

    private Long id;
    private String name;
    private String description;
    private int capacity;
    private float avgShipRating;
    private float avgRoomRating;
    private float avgCrewRating;
    private float avgFoodRating;

    public CruiseEntity(Long id, String name, String description, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }
}
