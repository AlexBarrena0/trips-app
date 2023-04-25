package edu.uoc.abarrena.trips;

import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.domain.service.DestinationServiceImpl;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.DestinationRepositoryImpl;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripConfiguration {

    @Bean
    public DestinationRepository destinationRepository(DestinationMapper destinationMapper) {
        return new DestinationRepositoryImpl(destinationMapper);
    }

    @Bean
    public DestinationService destinationService(DestinationRepository destinationRepository) {
        return new DestinationServiceImpl(destinationRepository);
    }
}
