package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public Long createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination findDestinationById(Long id) {
        return null;
    }

    @Override
    public List<Destination> findAllDestinations() {
        return null;
    }
}
