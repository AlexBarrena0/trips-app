package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.model.Rating;
import edu.uoc.abarrena.trips.domain.repository.RatingRepository;
import edu.uoc.abarrena.trips.domain.service.CruiseService;
import edu.uoc.abarrena.trips.domain.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final CruiseService cruiseService;

    public RatingServiceImpl(RatingRepository ratingRepository, CruiseService cruiseService) {
        this.ratingRepository = ratingRepository;
        this.cruiseService = cruiseService;
    }

    @Override
    public Long createRating(Rating rating) throws EntityNotFoundException {
        Cruise cruise = cruiseService.findCruiseById(rating.getCruise().getId());
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        Long ratingId = ratingRepository.save(rating);
        calculateAverageRatings(cruise);
        return ratingId;
    }

    @Override
    public List<Rating> findRatingByCruiseId(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseService.findCruiseById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        return ratingRepository.findByCruiseId(id);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id);
        ratingRepository.delete(id);
        calculateAverageRatings(rating.getCruise());
    }

    private void calculateAverageRatings(Cruise cruise) {
        List<Rating> ratings = ratingRepository.findByCruiseId(cruise.getId());
        Double averageShipRating = ratings.stream().mapToDouble(Rating::getShip).average().orElse(0.0);
        cruise.setAvgShipRating(averageShipRating);
        Double averageCrewRating = ratings.stream().mapToDouble(Rating::getCrew).average().orElse(0.0);
        cruise.setAvgCrewRating(averageCrewRating);
        Double averageFoodRating = ratings.stream().mapToDouble(Rating::getFood).average().orElse(0.0);
        cruise.setAvgFoodRating(averageFoodRating);
        Double averageRoomRating = ratings.stream().mapToDouble(Rating::getRoom).average().orElse(0.0);
        cruise.setAvgRoomRating(averageRoomRating);
        cruiseService.updateCruise(cruise);
    }
}
