package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.converter.CompanyConverter;
import edu.uoc.abarrena.trips.domain.converter.TravelerConverter;
import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.domain.model.Traveler;
import edu.uoc.abarrena.trips.domain.service.UserService;
import edu.uoc.abarrena.trips.infrastructure.clients.UserServiceClient;
import edu.uoc.abarrena.trips.security.SecurityHelper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserServiceClient userServiceClient;

    public UserServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Traveler getTraveler(Long id) {
        try {
            return TravelerConverter.INSTANCE.toDomain(userServiceClient.findTravelerById(SecurityHelper.getToken(), id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Company getCompany(Long id) {
        try {
            return CompanyConverter.INSTANCE.toDomain(userServiceClient.findCompanyById(SecurityHelper.getToken(), id));
        } catch (Exception e) {
            return null;
        }
    }
}
