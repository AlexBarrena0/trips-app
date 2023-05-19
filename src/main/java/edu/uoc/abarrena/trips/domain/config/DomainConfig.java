package edu.uoc.abarrena.trips.domain.config;

import edu.uoc.abarrena.trips.domain.service.CruiseService;
import edu.uoc.abarrena.trips.domain.service.DestinationService;
import edu.uoc.abarrena.trips.domain.service.RatingService;
import edu.uoc.abarrena.trips.domain.service.TripService;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.domain.repository.RatingRepository;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import edu.uoc.abarrena.trips.domain.service.impl.CruiseServiceImpl;
import edu.uoc.abarrena.trips.domain.service.impl.DestinationServiceImpl;
import edu.uoc.abarrena.trips.domain.service.impl.RatingServiceImpl;
import edu.uoc.abarrena.trips.domain.service.impl.TripServiceImpl;
import edu.uoc.abarrena.trips.infrastructure.clients.UserServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DestinationService destinationService(DestinationRepository destinationRepository) {
        return new DestinationServiceImpl(destinationRepository);
    }

    @Bean
    public CruiseService cruiseService(CruiseRepository cruiseRepository, UserServiceClient userServiceClient) {
        return new CruiseServiceImpl(cruiseRepository, userServiceClient);
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
