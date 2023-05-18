package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingDto {

    @NotBlank(message = "Id is required")
    private Long id;

    @NotBlank(message = "Status is required")
    private String status;
}
