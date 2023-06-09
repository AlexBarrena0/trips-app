package edu.uoc.abarrena.trips.domain.repository;

import java.util.List;

public interface CruiseImageRepository {

    /**
     * Save a cruise images
     *
     * @param cruiseId  the cruise id
     * @param imagesIds the images ids
     */
    void save(Long cruiseId, List<Long> imagesIds);

    /**
     * Find all the images of a cruise
     *
     * @param cruiseId the cruise id
     * @return the list of images of the cruise
     */
    List<Long> findByCruiseId(Long cruiseId);

    /**
     * Delete a cruise image
     *
     * @param cruiseId the cruise id
     */
    void deleteByCruiseId(Long cruiseId);
}
