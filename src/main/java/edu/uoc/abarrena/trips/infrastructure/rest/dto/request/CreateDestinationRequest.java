package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

import edu.uoc.abarrena.trips.domain.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDestinationRequest implements Request<Destination> {

    @NotBlank(message = "Destination description is required")
    private String description;

    @Override
    public Destination toDomain() {
        return new Destination().builder()
                .description(this.description)
                .build();
    }
}
