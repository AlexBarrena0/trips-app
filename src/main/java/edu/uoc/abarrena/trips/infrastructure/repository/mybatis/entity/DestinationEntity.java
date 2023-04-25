package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity;

import edu.uoc.abarrena.trips.domain.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationEntity {

    private Long id;
    private String description;

    public static DestinationEntity fromDomain(Destination destination) {
        if (destination == null) {
            return null;
        }
        return DestinationEntity.builder()
                .id(destination.getId())
                .description(destination.getDescription())
                .build();
    }
    public Destination toDomain() {
        return Destination.builder()
                .id(this.id)
                .description(this.description)
                .build();
    }

}
