package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.InconsistentDatesException;
import edu.uoc.abarrena.trips.domain.exceptions.NoAvailablePlacesException;
import edu.uoc.abarrena.trips.domain.exceptions.OverlappingTripException;
import edu.uoc.abarrena.trips.domain.model.*;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import edu.uoc.abarrena.trips.domain.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final DestinationService destinationService;

    private final CruiseService cruiseService;

    private final UserService userService;

    private final NotificationService notificationService;

    public TripServiceImpl(TripRepository tripRepository, DestinationService destinationService, CruiseService cruiseService, UserService userService, NotificationService notificationService) {
        this.tripRepository = tripRepository;
        this.destinationService = destinationService;
        this.cruiseService = cruiseService;
        this.userService = userService;
        this.notificationService = notificationService;
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
        trip.setAvailablePlaces(cruise.getCapacity());
        Long id = tripRepository.save(trip);
        sendNotification(trip);
        return id;
    }

    private void sendNotification(Trip trip) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("destinationId", trip.getDestination().getId());
        params.put("startDate", trip.getStartDate().toString());
        params.put("endDate", trip.getEndDate().toString());
        notificationService.sendNotification(new Notification(NotificationType.NEW_TRIP, params));
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
    public List<Trip> findTripByCompanyId(Long id) throws EntityNotFoundException {
        Company company = userService.getCompany(id);
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }
        Map<String, Object> params = Map.of("companyId", id);
        return tripRepository.search(params);
    }

    @Override
    public List<Trip> findTripByTravelerId(Long id) throws EntityNotFoundException {
        Traveler traveler = userService.getTraveler(id);
        if (traveler == null) {
            throw new EntityNotFoundException("Traveler not found");
        }
        Map<String, Object> params = Map.of("travelerId", id);
        return tripRepository.search(params);
    }

    @Override
    public List<Trip> findTripByDestinationIdAndDateRange(Long id, LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {
        Map<String, Object> params = new HashMap<>();
        params.put("destinationId", id);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return tripRepository.search(params);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripRepository.update(trip);
    }

    @Override
    public void bookPlace(Long tripId) {
        Trip trip = findTripById(tripId);
        if (trip.getAvailablePlaces() == 0) {
            throw new NoAvailablePlacesException();
        }
        trip.setAvailablePlaces(trip.getAvailablePlaces() - 1);
        updateTrip(trip);
    }

    private boolean isTripOverlapping(Trip trip) {
        Map<String, Object> params = Map.of("cruiseId", trip.getCruise().getId(), "startDate", trip.getStartDate(), "endDate", trip.getEndDate());
        List<Trip> trips = tripRepository.search(params);
        return trips.size() > 0;
    }
}
