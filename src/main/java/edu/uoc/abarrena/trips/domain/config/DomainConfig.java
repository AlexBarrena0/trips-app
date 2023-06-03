package edu.uoc.abarrena.trips.domain.config;

import edu.uoc.abarrena.trips.domain.model.Notification;
import edu.uoc.abarrena.trips.domain.repository.*;
import edu.uoc.abarrena.trips.domain.service.*;
import edu.uoc.abarrena.trips.domain.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class DomainConfig {

    @Bean
    public DestinationService destinationService(DestinationRepository destinationRepository) {
        return new DestinationServiceImpl(destinationRepository);
    }

    @Bean
    public CruiseService cruiseService(CruiseRepository cruiseRepository, UserService userService, CruiseImageRepository cruiseImageRepository) {
        return new CruiseServiceImpl(cruiseRepository, userService, cruiseImageRepository);
    }

    @Bean
    public TripService tripService(TripRepository tripRepository, DestinationService destinationService, CruiseService cruiseService, UserService userService, NotificationService notificationService) {
        return new TripServiceImpl(tripRepository, destinationService, cruiseService, userService, notificationService);
    }

    @Bean
    public RatingService ratingService(RatingRepository ratingRepository, CruiseService cruiseService) {
        return new RatingServiceImpl(ratingRepository, cruiseService);
    }

    @Bean
    public  BookingService bookingService(BookingRepository bookingRepository, TripService tripService, UserService userService, NotificationService notificationService) {
        return new BookingServiceImpl(bookingRepository, tripService, userService, notificationService);
    }

    @Bean
    public NotificationService notificationService(KafkaTemplate<String, Notification> notificationKafkaTemplate) {
        return new NotificationServiceImpl(notificationKafkaTemplate);
    }
}
