package edu.uoc.abarrena.trips.domain.config;

import edu.uoc.abarrena.trips.domain.repository.*;
import edu.uoc.abarrena.trips.domain.service.*;
import edu.uoc.abarrena.trips.domain.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DestinationService destinationService(DestinationRepository destinationRepository) {
        return new DestinationServiceImpl(destinationRepository);
    }

    @Bean
    public CruiseService cruiseService(CruiseRepository cruiseRepository, UserService userService) {
        return new CruiseServiceImpl(cruiseRepository, userService);
    }

    @Bean
    public TripService tripService(TripRepository tripRepository, DestinationService destinationService, CruiseService cruiseService) {
        return new TripServiceImpl(tripRepository, destinationService, cruiseService);
    }

    @Bean
    public RatingService ratingService(RatingRepository ratingRepository, CruiseService cruiseService) {
        return new RatingServiceImpl(ratingRepository, cruiseService);
    }

    @Bean
    public  BookingService bookingService(BookingRepository bookingRepository, TripService tripService, UserService userService) {
        return new BookingServiceImpl(bookingRepository, tripService, userService);
    }
}
