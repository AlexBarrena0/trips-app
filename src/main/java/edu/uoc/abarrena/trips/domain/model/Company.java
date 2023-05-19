package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private Long id;
    private String cif;
    private String name;
    private String address;
    private String description;
    private String phone;
    private String email;

    public Company(Long id) {
        this.id = id;
    }
}