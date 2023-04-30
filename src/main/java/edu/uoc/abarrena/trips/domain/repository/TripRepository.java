package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Trip;

import java.util.List;
import java.util.Map;

public interface TripRepository {

    /**
     * Save a trip
     * @param trip to save
     * @return the id of the saved trip
     */
    public Long save(Trip trip);

    /**
     * Finds a trip by its id
     * @param id of the trip to find
     * @return the trip with the given id
     */
    public Trip findById(Long id);

    /**
     * Finds all the trips that match the given parameters
     * @param params the parameters to search
     * @return all the trips that match the given parameters
     */
    public List<Trip> search(Map<String, Object> params);

    /**
     * Update a trip
     * @param trip the trip to update
     */
    public void update(Trip trip);


}
