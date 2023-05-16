package edu.uoc.abarrena.trips.infrastructure.rest;

import edu.uoc.abarrena.trips.application.RatingService;
import edu.uoc.abarrena.trips.application.converter.RatingConverter;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateRatingDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(RatingController.BASE_PATH)
public class RatingController {

        public static final String BASE_PATH = "/ratings";

        private final RatingService ratingService;

        public RatingController(RatingService ratingService) {
            this.ratingService = ratingService;
        }

        @PostMapping
        public void rateCruise(@RequestBody CreateRatingDto ratingDto) {
            log.trace("Rating cruise");

            ratingService.createRating(RatingConverter.INSTANCE.toDomain(ratingDto));
        }

        @DeleteMapping("/{id}")
        public void deleteRating(@PathVariable Long id) {
            log.trace("Deleting rating");

            ratingService.deleteRating(id);
        }

}
