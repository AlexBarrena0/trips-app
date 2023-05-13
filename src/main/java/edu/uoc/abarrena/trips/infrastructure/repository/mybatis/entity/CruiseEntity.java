package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CruiseEntity {

    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private Double avgShipRating;
    private Double avgRoomRating;
    private Double avgCrewRating;
    private Double avgFoodRating;

    public CruiseEntity(Long id, String name, String description, Integer capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }
}
