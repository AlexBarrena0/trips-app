package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.application.CruiseService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import org.springframework.stereotype.Service;

@Service
public class CruiseServiceImpl implements CruiseService {

    private final CruiseRepository cruiseRepository;

    public CruiseServiceImpl(CruiseRepository cruiseRepository) {
        this.cruiseRepository = cruiseRepository;
    }

    @Override
    public Long createCruise(Cruise cruise) {
        return cruiseRepository.save(cruise);
    }

    @Override
    public Cruise findCruiseById(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseRepository.findById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        return cruise;
    }

    @Override
    public void updateCruise(Cruise cruise) {
        cruiseRepository.update(cruise);
    }
}
