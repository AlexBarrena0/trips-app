package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.application.CruiseService;
import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.InconsistentDatesException;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import edu.uoc.abarrena.trips.factory.TripFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TripServiceUnitUnitTest extends BaseUnitTest {

    @Mock
    private DestinationService destinationService;

    @Mock
    private CruiseService cruiseService;

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    void createTrip_Success() {
        Trip trip = TripFactory.tripDomain(null);
        Long expectedId = 1L;

        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(trip.getDestination());
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(trip.getCruise());
        when(tripRepository.save(trip)).thenReturn(expectedId);

        Long id = tripService.createTrip(trip);
        assertEquals(expectedId, id);
    }

    @Test
    void createTrip_NonExistingDestination() {
        Trip trip = TripFactory.tripDomain(null);
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> tripService.createTrip(trip));
    }

    @Test
    void createTrip_NonExistingCruise() {
        Trip trip = TripFactory.tripDomain(null);
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(trip.getDestination());
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> tripService.createTrip(trip));
    }

    @Test
    void createTrip_InconsistentDates() {
        Trip trip = TripFactory.tripDomainWithInconsistentDates(null);
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(trip.getDestination());
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(trip.getCruise());

        assertThrows(InconsistentDatesException.class, () -> tripService.createTrip(trip));
    }

    @Test
    void findTripById_Success() {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripRepository.findById(trip.getId())).thenReturn(trip);

        Trip tripFound = tripService.findTripById(trip.getId());
        assertEquals(trip, tripFound);
    }

    @Test
    void findTripById_NotFound() {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripRepository.findById(trip.getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> tripService.findTripById(trip.getId()));
    }

    @Test
    void findTripByCruiseId_Success() {
        Trip trip = TripFactory.tripDomain(1L);
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(trip.getCruise());
        when(tripRepository.search(Map.of("cruiseId", trip.getCruise().getId()))).thenReturn(List.of(trip));

        List<Trip> tripsFound = tripService.findTripByCruiseId(trip.getCruise().getId());
        assertEquals(List.of(trip), tripsFound);
    }

    @Test
    void findTripByCruiseId_CruiseIdNotFound() {
        Trip trip = TripFactory.tripDomain(1L);
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> tripService.findTripByCruiseId(trip.getCruise().getId()));
    }

    @Test
    void findTripByDestinationIdAndDateRange_Success() {
        Trip trip = TripFactory.tripDomain(1L);
        Map<String, Object> params = Map.of("destinationId", trip.getDestination().getId(), "startDate", trip.getStartDate(), "endDate", trip.getEndDate());
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(trip.getDestination());
        when(tripRepository.search(params)).thenReturn(List.of(trip));

        List<Trip> tripsFound = tripService.findTripByDestinationIdAndDateRange(trip.getDestination().getId(), trip.getStartDate(), trip.getEndDate());
        assertEquals(List.of(trip), tripsFound);
    }

    @Test
    void findTripByDestinationIdAndDateRange_DestinationIdNotFound() {
        Trip trip = TripFactory.tripDomain(1L);
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> tripService.findTripByDestinationIdAndDateRange(trip.getDestination().getId(), trip.getStartDate(), trip.getEndDate()));
    }

    @Test
    void updateTrip_Success() {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripRepository.findById(trip.getId())).thenReturn(trip);
        when(destinationService.findDestinationById(trip.getDestination().getId())).thenReturn(trip.getDestination());
        when(cruiseService.findCruiseById(trip.getCruise().getId())).thenReturn(trip.getCruise());
        doNothing().when(tripRepository).update(trip);

        tripService.updateTrip(trip);
    }
}
