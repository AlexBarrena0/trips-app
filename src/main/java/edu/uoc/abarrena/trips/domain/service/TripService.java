package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.InconsistentDatesException;
import edu.uoc.abarrena.trips.domain.model.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripService {

    /**
     * Create a new trip
     *
     * @param trip
     * @return the id of the created trip
     * @throws EntityNotFoundException if the cruise or the destination does not exist
     */
    Long createTrip(Trip trip) throws EntityNotFoundException, InconsistentDatesException;

    /**
     * Find a trip by its id
     *
     * @param id the id of the trip to find
     * @return the trip with the given id
     * @throws EntityNotFoundException if the trip does not exist
     */
    Trip findTripById(Long id) throws EntityNotFoundException;

    /**
     * Find a trip by its cruise id
     *
     * @param id the id of the cruise to find
     * @return the trips with the given cruise id
     * @throws EntityNotFoundException if the cruise does not exist
     */
    List<Trip> findTripByCruiseId(Long id) throws EntityNotFoundException;

    /**
     * Find a trip by its company id
     *
     * @param id the id of the company to find
     * @return the trips with the given company id
     * @throws EntityNotFoundException if the company does not exist
     */
    List<Trip> findTripByCompanyId(Long id) throws EntityNotFoundException;

    /**
     * Find a trip by its traveler id
     *
     * @param id the id of the traveler to find
     * @return the trips with the given traveler id
     * @throws EntityNotFoundException if the traveler does not exist
     */
    List<Trip> findTripByTravelerId(Long id) throws EntityNotFoundException;

    /**
     * Find a trip by its destination id and date range
     *
     * @param id        the id of the destination to find
     * @param startDate the start date of the trip
     * @param endDate   the end date of the trip
     * @return the trips with the given destination id and date range
     * @throws EntityNotFoundException if the destination does not exist
     */
    List<Trip> findTripByDestinationIdAndDateRange(Long id, LocalDate startDate, LocalDate endDate) throws EntityNotFoundException;

    /**
     * Update a trip
     *
     * @param trip the trip to update
     */
    void updateTrip(Trip trip);

    /**
     * Books a place in the selected trip
     *
     * @param tripId the id of the trip to book the place
     */
    void bookPlace(Long tripId);
}
