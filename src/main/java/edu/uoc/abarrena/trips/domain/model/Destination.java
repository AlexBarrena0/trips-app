package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destination {

    private Long id;
    private String description;

    public Destination(Long id) {
        this.id = id;
    }
}
