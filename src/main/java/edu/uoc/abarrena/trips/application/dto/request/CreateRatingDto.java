package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingDto {

    private Long cruiseId;
    private String comment;
    private Integer ship;
    private Integer room;
    private Integer crew;
    private Integer food;
}
