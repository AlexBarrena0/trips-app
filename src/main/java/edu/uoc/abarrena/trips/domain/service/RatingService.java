package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Rating;

import java.util.List;

public interface RatingService {
    /**
     * Creates a new rating
     *
     * @param rating the rating to create
     * @return the id of the created rating
     * @throws EntityNotFoundException if the cruise does not exist
     */
    Long createRating(Rating rating) throws EntityNotFoundException;

    /**
     * Find a rating by its cruise
     *
     * @param id the id of the cruise to find
     * @return the trips with the given cruise
     * @throws EntityNotFoundException if the cruise does not exist
     */
    List<Rating> findRatingByCruiseId(Long id) throws EntityNotFoundException;

    /**
     * Deletes a rating
     *
     * @param id the id of the rating to delete
     */
    void deleteRating(Long id);
}
