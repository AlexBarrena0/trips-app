package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {
    private Long id;
    private Integer ship;
    private Integer room;
    private Integer crew;
    private Integer food;
    private String comment;
    private CruiseEntity cruise;
}
