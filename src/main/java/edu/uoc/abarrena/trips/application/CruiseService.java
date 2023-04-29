package edu.uoc.abarrena.trips.application;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Cruise;

/**
 * The cruise service
 */
public interface CruiseService {

    /**
     * Creates a new cruise
     * @param cruise the cruise to create
     * @return the id of the created cruise
     */
    public Long createCruise(Cruise cruise);

    /**
     * Finds a cruise by its id
     * @param id the id of the cruise to find
     * @return the cruise with the given id
     * @throws EntityNotFoundException if the cruise is not found
     */
    public Cruise findCruiseById(Long id) throws EntityNotFoundException;

    /**
     * Updates a cruise
     * @param cruise the cruise to update
     */
    public void updateCruise(Cruise cruise);

}
