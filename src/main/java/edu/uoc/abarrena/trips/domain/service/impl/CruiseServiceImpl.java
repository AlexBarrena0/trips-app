package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseImageRepository;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.service.CruiseService;
import edu.uoc.abarrena.trips.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CruiseServiceImpl implements CruiseService {

    private final CruiseRepository cruiseRepository;

    private final CruiseImageRepository cruiseImageRepository;

    private final UserService userService;

    public CruiseServiceImpl(CruiseRepository cruiseRepository, UserService userService, CruiseImageRepository cruiseImageRepository) {
        this.cruiseRepository = cruiseRepository;
        this.userService = userService;
        this.cruiseImageRepository = cruiseImageRepository;
    }

    @Override
    public Long createCruise(Cruise cruise) {
        Company company = userService.getCompany(cruise.getCompany().getId());
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }
        Long id = cruiseRepository.save(cruise);
        if (cruise.getImagesIds() != null && !cruise.getImagesIds().isEmpty()) {
            cruiseImageRepository.save(id, cruise.getImagesIds());
        }
        return id;
    }

    @Override
    public Cruise findCruiseById(Long id) throws EntityNotFoundException {
        Cruise cruise = cruiseRepository.findById(id);
        if (cruise == null) {
            throw new EntityNotFoundException("Cruise not found");
        }
        Company company = userService.getCompany(cruise.getCompany().getId());
        cruise.setCompany(company);
        return cruise;
    }

    @Override
    public List<Cruise> findByCompanyId(Long id) throws EntityNotFoundException {
        Company company = userService.getCompany(id);
        if (company == null) {
            throw new EntityNotFoundException("Company not found");
        }
        return cruiseRepository.findByCompanyId(id);
    }

    @Override
    public void updateCruise(Cruise cruise) {
        cruiseRepository.update(cruise);
        cruiseImageRepository.deleteByCruiseId(cruise.getId());
        if (cruise.getImagesIds() != null && !cruise.getImagesIds().isEmpty()) {
            cruiseImageRepository.save(cruise.getId(), cruise.getImagesIds());
        }
    }

    @Override
    public void deleteCruise(Long id) {
        cruiseRepository.delete(id);
    }

}
