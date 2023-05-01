package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.domain.exceptions.DestinationDuplicatedException;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.factory.DestinationFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DestinationServiceUnitUnitTest extends BaseUnitTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationServiceImpl destinationService;

    @Test
    void createDestination_Success() {
        Destination destination = DestinationFactory.destinationDomain(null);
        Long expectedId = 1L;
        when(destinationRepository.findByName(destination.getDescription())).thenReturn(null);
        when(destinationRepository.save(destination)).thenReturn(1L);
        Long id = destinationService.createDestination(destination);
        assertEquals(expectedId, id);
    }

    @Test
    void createDestination_ErrorDuplicate() {
        Destination destination = DestinationFactory.destinationDomain(null);
        when(destinationRepository.findByName(destination.getDescription())).thenReturn(destination);
        assertThrows(DestinationDuplicatedException.class, () -> destinationService.createDestination(destination));
    }

    @Test
    void findDestinationById_Success() {
        Long id = 1L;
        Destination destination = DestinationFactory.destinationDomain(id);
        when(destinationRepository.findById(1L)).thenReturn(destination);
        Destination result = destinationService.findDestinationById(id);
        assertEquals(destination, result);
    }

    @Test
    void findDestinationById_NotFound() {
        Long id = 1L;
        when(destinationRepository.findById(id)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> destinationService.findDestinationById(id));
    }

    @Test
    void findAllDestinations_Success() {
        List<Destination> destinations = DestinationFactory.destinationListDomain();
        when(destinationRepository.findAll()).thenReturn(destinations);
        List<Destination> result = destinationService.findAllDestinations();
        assertEquals(destinations, result);
    }
}

