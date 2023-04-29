package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.domain.exceptions.DestinationDuplicatedException;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DestinationServiceUnitTest extends BaseTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationServiceImpl destinationService;

    @Test
    void createDestination_Success() throws DestinationDuplicatedException {
        Destination destination = new Destination(null, "Maldives");
        when(destinationRepository.findByName(destination.getDescription())).thenReturn(null);
        when(destinationRepository.save(destination)).thenReturn(1L);
        Long id = destinationService.createDestination(destination);
        assertEquals(1L, id);
    }

    @Test
    void createDestination_ErrorDuplicate() {
        Destination destination = new Destination(1L, "Maldives");
        when(destinationRepository.findByName(destination.getDescription())).thenReturn(destination);
        assertThrows(DestinationDuplicatedException.class, () -> destinationService.createDestination(destination));
    }

    @Test
    void findDestinationById_Success() {
        Destination destination = new Destination(1L, "Maldives");
        when(destinationRepository.findById(1L)).thenReturn(destination);
        Destination result = destinationService.findDestinationById(1L);
        assertEquals(destination, result);
    }

    @Test
    void findDestinationById_NotFound() {
        when(destinationRepository.findById(1L)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> destinationService.findDestinationById(1L));
    }

    @Test
    void findAllDestinations_Success() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(1L, "Maldives"));
        destinations.add(new Destination(2L, "Red Sea"));
        when(destinationRepository.findAll()).thenReturn(destinations);
        List<Destination> result = destinationService.findAllDestinations();
        assertEquals(destinations, result);
    }
}

