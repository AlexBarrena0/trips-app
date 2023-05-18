package edu.uoc.abarrena.trips.application.rest;

import edu.uoc.abarrena.trips.domain.service.RatingService;
import edu.uoc.abarrena.trips.domain.converter.RatingConverter;
import edu.uoc.abarrena.trips.application.dto.request.CreateRatingDto;
import edu.uoc.abarrena.trips.application.dto.response.Result;
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
        public Result<Long> rateCruise(@RequestBody CreateRatingDto ratingDto) {
            log.trace("Rating cruise");

            Long id = ratingService.createRating(RatingConverter.INSTANCE.toDomain(ratingDto));

            return new Result<>(id, "Rating created successfully");
        }

        @DeleteMapping("/{id}")
        public Result<Boolean> deleteRating(@PathVariable Long id) {
            log.trace("Deleting rating");

            ratingService.deleteRating(id);

            return new Result<>(true, "Rating deleted successfully");
        }

}
