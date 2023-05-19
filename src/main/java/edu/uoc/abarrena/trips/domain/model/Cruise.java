package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cruise {

    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private Double avgShipRating;
    private Double avgRoomRating;
    private Double avgCrewRating;
    private Double avgFoodRating;
    private Company company;

    public Cruise(Long id) {
        this.id = id;
    }

    public Cruise(Long id, String name, String description, int capacity, Long companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.company = new Company(companyId);
    }

}
