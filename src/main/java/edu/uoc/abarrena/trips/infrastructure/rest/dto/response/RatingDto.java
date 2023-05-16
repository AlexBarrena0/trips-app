package edu.uoc.abarrena.trips.infrastructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private Long id;
    private Integer ship;
    private Integer room;
    private Integer crew;
    private Integer food;
    private String comment;
}
