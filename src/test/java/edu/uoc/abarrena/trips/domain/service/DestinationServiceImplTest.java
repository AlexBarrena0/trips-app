package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.DestinationTestFactory;
import edu.uoc.abarrena.trips.application.DestinationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class DestinationServiceImplTest {

    @Autowired
    private DestinationService destinationService;

    @Test
    public void createDestination() {
        Long id = destinationService.createDestination(DestinationTestFactory.createDestination());
        assertNotNull(id);
    }
}
