package edu.uoc.abarrena.trips.domain.converter;

import edu.uoc.abarrena.trips.domain.model.Company;
import edu.uoc.abarrena.trips.infrastructure.clients.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface CompanyConverter {

    public final CompanyConverter INSTANCE = Mappers.getMapper(CompanyConverter.class);


    default Company toDomain(Result<HashMap<String, Object>> companyResult) {
        HashMap<String, Object> hashMap = companyResult.getResponse();
        Company company = new Company();
        company.setId(Long.parseLong(((Integer) hashMap.get("id")).toString()));
        company.setCif((String) hashMap.get("cif"));
        company.setName((String) hashMap.get("name"));
        company.setAddress((String) hashMap.get("address"));
        company.setDescription((String) hashMap.get("description"));
        company.setPhone((String) hashMap.get("phone"));
        company.setEmail((String) hashMap.get("email"));
        return company;
    }
}
