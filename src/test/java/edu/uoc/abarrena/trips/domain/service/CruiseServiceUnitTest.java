package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CruiseServiceUnitTest extends BaseTest {

    @Mock
    private CruiseRepository cruiseRepository;

    @InjectMocks
    private CruiseServiceImpl cruiseService;

    @Test
    void createCruise_Success() {
        Cruise cruise = new Cruise(null, "Cruise 1", "Cruise 1 description", 10);
        Long expectedId = 1L;
        when(cruiseRepository.save(cruise)).thenReturn(expectedId);
        Long id = cruiseService.createCruise(cruise);
        assertEquals(expectedId, id);
    }

    @Test
    void findCruiseById_Success() {
        Long id = 1L;
        Cruise expectedCruise = new Cruise(id, "Cruise 1", "Cruise 1 description",10);
        when(cruiseRepository.findById(id)).thenReturn(expectedCruise);
        Cruise actualCruise = cruiseService.findCruiseById(id);
        assertEquals(expectedCruise, actualCruise);
    }

    @Test
    void findCruiseById_NotFound() {
        Long id = 1L;
        when(cruiseRepository.findById(id)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> cruiseService.findCruiseById(id));
    }

    @Test
    void updateCruise_Success() {
        Long id = 1L;
        Cruise cruise = new Cruise(id, "Cruise 1", "Cruise 1 description", 10);
        doNothing().when(cruiseRepository).update(cruise);
        cruiseService.updateCruise(cruise);
    }
}
