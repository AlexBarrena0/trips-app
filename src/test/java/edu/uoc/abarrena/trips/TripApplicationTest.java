package edu.uoc.abarrena.trips;

import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.infrastructure.rest.DestinationController;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TripApplicationTest {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private DestinationController destinationController;

    @Test
    void contextLoads() {
        assertNotNull(destinationService);
        assertNotNull(destinationRepository);
        assertNotNull(destinationController);
    }
}
