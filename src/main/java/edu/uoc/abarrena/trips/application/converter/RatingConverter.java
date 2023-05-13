package edu.uoc.abarrena.trips.application.converter;

import edu.uoc.abarrena.trips.domain.model.Rating;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RatingConverter {

    public static final RatingConverter INSTANCE = Mappers.getMapper(RatingConverter.class);

    RatingEntity toEntity(Rating rating);
}
