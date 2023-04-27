package edu.uoc.abarrena.trips;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationRequest;

public class DestinationTestFactory {

    public static CreateDestinationRequest createDestinationRequest() {
        return new CreateDestinationRequest("Destination " + System.currentTimeMillis() + " description");
    }

    public static Destination createDestination() {
        return new Destination().builder()
                .description("Destination " + System.currentTimeMillis() + " description")
                .build();
    }
}
