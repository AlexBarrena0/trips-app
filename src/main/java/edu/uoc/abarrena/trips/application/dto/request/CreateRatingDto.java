package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingDto {

    @NotBlank(message = "The cruise id is required")
    private Long cruiseId;
    private String comment;
    @NotBlank(message = "The ship rating is required")
    @Min(value = 0, message = "The rating must be between 0 and 5")
    @Max(value = 5, message = "The rating must be between 0 and 5")
    private Integer ship;
    @NotBlank(message = "The room rating is required")
    @Min(value = 0, message = "The rating must be between 0 and 5")
    @Max(value = 5, message = "The rating must be between 0 and 5")
    private Integer room;
    @NotBlank(message = "The crew rating is required")
    @Min(value = 0, message = "The rating must be between 0 and 5")
    @Max(value = 5, message = "The rating must be between 0 and 5")
    private Integer crew;
    @NotBlank(message = "The food rating is required")
    @Min(value = 0, message = "The rating must be between 0 and 5")
    @Max(value = 5, message = "The rating must be between 0 and 5")
    private Integer food;
}
