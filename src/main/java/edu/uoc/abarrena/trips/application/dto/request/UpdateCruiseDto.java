package edu.uoc.abarrena.trips.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCruiseDto {

    @NotBlank(message = "Cruise id is required")
    private Long id;

    @NotBlank(message = "Cruise name is required")
    @Max(value = 255, message = "Cruise name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Cruise description is required")
    @Max(value = 5000, message = "Cruise description must be less than 5000 characters")
    private String description;

    @NotBlank(message = "Cruise capacity is required")
    @Min(value = 1, message = "Cruise capacity must be greater than 0")
    private int capacity;

    private Long thumbnailId;

    private List<Long> imagesIds;
}
