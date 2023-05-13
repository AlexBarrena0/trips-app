package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;

import java.util.List;

public interface RatingRepository {
    /**
     * Save a rating
     * @param ratingEntity to save
     * @return the id of the saved rating
     */
    public Long save(RatingEntity ratingEntity);

    /**
     * Finds all the ratings of a cruise
     * @param id of the cruise to find
     * @return all the cruise's ratings
     */
    public List<RatingEntity> findByCruiseId(Long id);

    /**
     * Deletes a rating
     * @param id of the rating to delete
     */
    public void delete(Long id);
}
