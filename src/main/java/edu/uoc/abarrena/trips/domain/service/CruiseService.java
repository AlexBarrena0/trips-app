package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.PendingTripsException;
import edu.uoc.abarrena.trips.domain.model.Cruise;

import java.util.List;

/**
 * The cruise service
 */
public interface CruiseService {

    /**
     * Creates a new cruise
     *
     * @param cruise the cruise to create
     * @return the id of the created cruise
     */
    Long createCruise(Cruise cruise);

    /**
     * Finds a cruise by its id
     *
     * @param id the id of the cruise to find
     * @return the cruise with the given id
     * @throws EntityNotFoundException if the cruise is not found
     */
    Cruise findCruiseById(Long id) throws EntityNotFoundException;

    /**
     * Finds all cruises by its company id
     *
     * @param id the id of the company to find
     * @return the cruises with the given company id
     * @throws EntityNotFoundException if the company is not found
     */
    List<Cruise> findByCompanyId(Long id) throws EntityNotFoundException;

    /**
     * Updates a cruise
     *
     * @param cruise the cruise to update
     */
    void updateCruise(Cruise cruise);

    /**
     * Deletes a cruise
     *
     * @param id the id of the cruise to delete
     */
    void deleteCruise(Long id) throws PendingTripsException;

}
