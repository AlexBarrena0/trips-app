package edu.uoc.abarrena.trips.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CruiseDto {

    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private Double avgShipRating;
    private Double avgRoomRating;
    private Double avgCrewRating;
    private Double avgFoodRating;
    private List<RatingDto> ratings;
}
