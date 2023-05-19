package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.converter.CompanyConverter;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.service.CruiseService;
import edu.uoc.abarrena.trips.infrastructure.clients.UserServiceClient;
import org.springframework.stereotype.Service;

@Service
public class CruiseServiceImpl implements CruiseService {

    private final CruiseRepository cruiseRepository;

    private final UserServiceClient userServiceClient;

    public CruiseServiceImpl(CruiseRepository cruiseRepository, UserServiceClient userServiceClient) {
        this.cruiseRepository = cruiseRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Long createCruise(Cruise cruise) {
        findCompanyById(cruise.getCompany().getId());
        return cruiseRepository.save(cruise);
    }

    @Override
    public Cruise findCruiseById(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseRepository.findById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        Company company = findCompanyById(cruise.getCompany().getId());
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

    private Company findCompanyById(Long id) {
        try {
            return CompanyConverter.INSTANCE.toDomain(userServiceClient.findCompanyById(id));
        } catch (Exception e) {
            throw new EntityNotFoundException("Company not found");
        }
    }
}
