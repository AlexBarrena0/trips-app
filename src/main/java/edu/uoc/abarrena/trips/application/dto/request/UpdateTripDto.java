package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTripDto {

    @NotBlank(message = "Trip id is required")
    private Long id;

    @NotBlank(message = "Trip route is required")
    @Max(value = 5000, message = "Trip route must be less than 5000 characters")
    private String route;

    @NotBlank(message = "Trip start date is required")
    private LocalDate startDate;

    @NotBlank(message = "Trip end date is required")
    private LocalDate endDate;

    @NotBlank(message = "Trip number of dives is required")
    @Min(value = 1, message = "Trip number of dives must be greater than 0")
    private Integer dives;

    @NotBlank(message = "Trip price is required")
    @Min(value = 0, message = "Trip price must be greater or equal than 0")
    private Double price;
}
