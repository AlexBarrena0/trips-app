package edu.uoc.abarrena.trips.domain.converter;

import edu.uoc.abarrena.trips.domain.model.Traveler;
import edu.uoc.abarrena.trips.infrastructure.clients.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface TravelerConverter {

    public final TravelerConverter INSTANCE = Mappers.getMapper(TravelerConverter.class);


    default Traveler toDomain(Result<HashMap<String, Object>> companyResult) {
        HashMap<String, Object> hashMap = companyResult.getResponse();
        Traveler traveler = new Traveler();
        traveler.setId(Long.parseLong(((Integer) hashMap.get("id")).toString()));
        return traveler;
    }
}
