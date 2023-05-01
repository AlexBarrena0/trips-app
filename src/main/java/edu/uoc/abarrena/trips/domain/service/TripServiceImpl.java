package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.application.CruiseService;
import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.application.TripService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.InconsistentDatesException;
import edu.uoc.abarrena.trips.domain.exceptions.OverlappingTripException;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final DestinationService destinationService;

    private final CruiseService cruiseService;

    public TripServiceImpl(TripRepository tripRepository, DestinationService destinationService, CruiseService cruiseService) {
        this.tripRepository = tripRepository;
        this.destinationService = destinationService;
        this.cruiseService = cruiseService;
    }

    @Override
    public Long createTrip(Trip trip) throws EntityNotFoundException {
        Destination destination = destinationService.findDestinationById(trip.getDestination().getId());
        if (destination == null) {
            throw new EntityNotFoundException("Destination not found");
        }
        Cruise cruise = cruiseService.findCruiseById(trip.getCruise().getId());
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        if (trip.getStartDate().isAfter(trip.getEndDate())) {
            throw new InconsistentDatesException();
        }
        if (isTripOverlapping(trip)) {
            throw new OverlappingTripException();
        }
        return tripRepository.save(trip);
    }

    @Override
    public Trip findTripById(Long id) throws EntityNotFoundException {
        Trip trip = tripRepository.findById(id);
        if (trip == null) {
            throw new EntityNotFoundException("Trip not found");
        }
        return trip;
    }

    @Override
    public List<Trip> findTripByCruiseId(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseService.findCruiseById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        Map<String, Object> params = Map.of("cruiseId", id);
        return tripRepository.search(params);
    }

    @Override
    public List<Trip> findTripByDestinationIdAndDateRange(Long id, LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {
        Destination destination = destinationService.findDestinationById(id);
        if (destination == null) {
            throw new EntityNotFoundException("Destination not found");
        }
        Map<String, Object> params = Map.of("destinationId", id, "startDate", startDate, "endDate", endDate);
        return tripRepository.search(params);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripRepository.update(trip);
    }

    private boolean isTripOverlapping(Trip trip) {
        Map<String, Object> params = Map.of("cruiseId", trip.getCruise().getId(), "startDate", trip.getStartDate(), "endDate", trip.getEndDate());
        List<Trip> trips = tripRepository.search(params);
        if (trips.size() > 0) {
            return true;
        }
        return false;
    }
}
