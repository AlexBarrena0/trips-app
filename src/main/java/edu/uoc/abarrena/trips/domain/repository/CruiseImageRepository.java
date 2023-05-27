package edu.uoc.abarrena.trips.domain.repository;

import java.util.List;

public interface CruiseImageRepository {

    /**
     * Save a cruise images
     * @param cruiseId the cruise id
     * @param imageIds
     */
    public void save(Long cruiseId, List<Long> imageIds);

    /**
     * Find all the images of a cruise
     * @param cruiseId the cruise id
     * @return the list of images of the cruise
     */
    public List<Long> findByCruiseId(Long cruiseId);

    /**
     * Delete a cruise image
     * @param imageId the cruise id
     */
    public void deleteByImageId(Long imageId);
}
