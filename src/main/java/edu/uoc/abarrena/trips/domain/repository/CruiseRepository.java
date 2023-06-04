package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Cruise;

import java.util.List;

public interface CruiseRepository {

    /**
     * Save a cruise
     *
     * @param cruise to save
     * @return the id of the saved cruise
     */
    Long save(Cruise cruise);

    /**
     * Finds a cruise by its id
     *
     * @param id of the cruise to find
     * @return the cruise with the given id
     */
    Cruise findById(Long id);

    /**
     * Finds all cruises by its company id
     *
     * @param id of the company to find
     * @return the cruises with the given company id
     */
    List<Cruise> findByCompanyId(Long id);

    /**
     * Update a cruise
     *
     * @param cruise the cruise to update
     */
    void update(Cruise cruise);

    /**
     * Delete a cruise
     *
     * @param id of the cruise to delete
     */
    void delete(Long id);
}
