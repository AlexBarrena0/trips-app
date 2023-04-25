package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Destination;

import java.util.List;

public interface DestinationRepository {

    /**
     * Save a destination
     * @param destination to save
     * @return the id of the saved destination
     */
    public Long save(Destination destination);

    /**
     * Finds a destination by its id
     * @param id of the destination to find
     * @return the destination with the given id
     */
    public Destination findById(Long id);

    /**
     * Finds a destination by its name
     * @param name of the destination to find
     * @return the destination with the given name
     */
    public Destination findByName(String name);

    /**
     * Finds all the destinations
     * @return all the destinations
     */
    public List<Destination> findAll();
}
