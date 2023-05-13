package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.application.RatingService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Override
    public Long createRating(Rating rating) {
        return null;
    }

    @Override
    public List<Rating> findRatingByCruiseId(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteRating(Long id) {

    }
}
