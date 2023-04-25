package edu.uoc.abarrena.trips.application;

import edu.uoc.abarrena.trips.domain.model.Destination;

import java.util.List;

/**
 * The destination service
 */
public interface DestinationService {

    /**
     * Creates a new destination
     * @param destination the destination to create
     * @return the id of the created destination
     */
    public Long createDestination(Destination destination);

    /**
     * Finds a destination by its id
     * @param id the id of the destination to find
     * @return the destination with the given id
     */
    public Destination findDestinationById(Long id);

    /**
     * Finds all the destinations
     * @return all the destinations
     */
    public List<Destination> findAllDestinations();
}
