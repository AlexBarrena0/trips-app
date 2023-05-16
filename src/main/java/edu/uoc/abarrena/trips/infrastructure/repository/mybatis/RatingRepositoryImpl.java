package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.application.converter.RatingConverter;
import edu.uoc.abarrena.trips.domain.model.Rating;
import edu.uoc.abarrena.trips.domain.repository.RatingRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.RatingMapper;

import java.util.List;

public class RatingRepositoryImpl implements RatingRepository {

    private final RatingMapper ratingMapper;

    public RatingRepositoryImpl(RatingMapper ratingMapper) {
        this.ratingMapper = ratingMapper;
    }

    @Override
    public Long save(Rating rating) {
        RatingEntity ratingEntity = RatingConverter.INSTANCE.toEntity(rating);
        ratingMapper.save(ratingEntity);
        return ratingEntity.getId();
    }

    @Override
    public List<Rating> findByCruiseId(Long id) {
        return RatingConverter.INSTANCE.toModel(ratingMapper.findByCruiseId(id));
    }

    @Override
    public void delete(Long id) {
        ratingMapper.delete(id);
    }
}
