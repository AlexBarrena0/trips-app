package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Cruise;

public interface CruiseRepository {

    /**
     * Save a cruise
     * @param cruise to save
     * @return the id of the saved cruise
     */
    public Long save(Cruise cruise);

    /**
     * Finds a cruise by its id
     * @param id of the cruise to find
     * @return the cruise with the given id
     */
    public Cruise findById(Long id);

    /**
     * Update a cruise
     * @param cruise the cruise to update
     */
    public void update(Cruise cruise);
}
