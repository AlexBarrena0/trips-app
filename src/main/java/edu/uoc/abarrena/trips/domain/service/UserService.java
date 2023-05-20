package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.domain.model.Traveler;

public interface UserService {

    /**
     * Finds a traveler by its id
     * @param id the id of the traveler to find
     * @return the traveler
     */
    public Traveler getTraveler(Long id);

    /**
     * Finds a company by its id
     * @param id the id of the company to find
     * @return the company
     */
    public Company getCompany(Long id);
}
