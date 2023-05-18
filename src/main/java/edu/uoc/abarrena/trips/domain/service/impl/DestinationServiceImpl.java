package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.service.DestinationService;
import edu.uoc.abarrena.trips.domain.exceptions.DestinationDuplicatedException;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
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
    public Long createDestination(Destination destination) throws DestinationDuplicatedException {
        if (destinationRepository.findByName(destination.getDescription()) != null) {
            throw new DestinationDuplicatedException();
        }
        return destinationRepository.save(destination);
    }

    @Override
    public Destination findDestinationById(Long id) throws EntityNotFoundException {
        Destination destination = destinationRepository.findById(id);
        if (destination == null) {
            throw new EntityNotFoundException("Destination not found");
        }
        return destination;
    }

    @Override
    public List<Destination> findAllDestinations() {
        return destinationRepository.findAll();
    }
}
