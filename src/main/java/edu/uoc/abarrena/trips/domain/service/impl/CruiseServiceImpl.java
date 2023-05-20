package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.service.CruiseService;
import edu.uoc.abarrena.trips.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CruiseServiceImpl implements CruiseService {

    private final CruiseRepository cruiseRepository;

    private final UserService userService;

    public CruiseServiceImpl(CruiseRepository cruiseRepository, UserService userService) {
        this.cruiseRepository = cruiseRepository;
        this.userService = userService;
    }

    @Override
    public Long createCruise(Cruise cruise) {
        Company company = userService.getCompany(cruise.getCompany().getId());
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }
        return cruiseRepository.save(cruise);
    }

    @Override
    public Cruise findCruiseById(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseRepository.findById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        Company company = userService.getCompany(id);
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }
        cruise.setCompany(company);
        return cruise;
    }

    @Override
    public void updateCruise(Cruise cruise) {
        cruiseRepository.update(cruise);
    }

    @Override
    public void deleteCruise(Long id) {
        cruiseRepository.delete(id);
    }

}
