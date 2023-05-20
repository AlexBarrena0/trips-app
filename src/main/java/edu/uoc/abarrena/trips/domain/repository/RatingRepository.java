package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Rating;

import java.util.List;

public interface RatingRepository {
    /**
     * Save a rating
     * @param ratingEntity to save
     * @return the id of the saved rating
     */
    public Long save(Rating rating);

    /**
     * Finds a rating by its id
     * @param id of the rating to find
     * @return the rating with the given id
     */
    public Rating findById(Long id);

    /**
     * Finds all the ratings of a cruise
     * @param id of the cruise to find
     * @return all the cruise's ratings
     */
    public List<Rating> findByCruiseId(Long id);

    /**
     * Deletes a rating
     * @param id of the rating to delete
     */
    public void delete(Long id);
}
