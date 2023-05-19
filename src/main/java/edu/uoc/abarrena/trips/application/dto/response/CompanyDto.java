package edu.uoc.abarrena.trips.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDto {

    private Long id;
    private String cif;
    private String name;
    private String address;
    private String description;
    private String phone;
    private String email;
}
