package edu.uoc.abarrena.trips.factory;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;

import java.util.Arrays;
import java.util.List;

public interface DestinationFactory {

    public static Destination destinationDomain(Long expectedId) {
        return new Destination(expectedId, "Destination 1");
    }

    public static DestinationEntity destinationEntity(Long expectedId) {
        return new DestinationEntity(expectedId, "Destination 1");
    }

    public static List<Destination> destinationListDomain() {
        return Arrays.asList(new Destination(1L, "Destination 1"), new Destination(2L, "Destination 2"));
    }

    public static List<DestinationEntity> destinationListEntity() {
        return Arrays.asList(new DestinationEntity(1L, "Destination 1"), new DestinationEntity(2L, "Destination 2"));
    }

    public static CreateDestinationDto createDestinationDto() {
        return new CreateDestinationDto("Destination 3");
    }

    public static CreateDestinationDto createExistingDestinationDto() {
        return new CreateDestinationDto("Destination 1");
    }
}
