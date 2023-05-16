package edu.uoc.abarrena.trips.domain.config;

import edu.uoc.abarrena.trips.application.CruiseService;
import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.application.RatingService;
import edu.uoc.abarrena.trips.application.TripService;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.domain.repository.RatingRepository;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import edu.uoc.abarrena.trips.domain.service.CruiseServiceImpl;
import edu.uoc.abarrena.trips.domain.service.DestinationServiceImpl;
import edu.uoc.abarrena.trips.domain.service.RatingServiceImpl;
import edu.uoc.abarrena.trips.domain.service.TripServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DestinationService destinationService(DestinationRepository destinationRepository) {
        return new DestinationServiceImpl(destinationRepository);
    }

    @Bean
    public CruiseService cruiseService(CruiseRepository cruiseRepository) {
        return new CruiseServiceImpl(cruiseRepository);
    }

    @Bean
    public TripService tripService(TripRepository tripRepository, DestinationService destinationService, CruiseService cruiseService) {
        return new TripServiceImpl(tripRepository, destinationService, cruiseService);
    }

    @Bean
    public RatingService ratingService(RatingRepository ratingRepository, CruiseService cruiseService) {
        return new RatingServiceImpl(ratingRepository, cruiseService);
    }
}
