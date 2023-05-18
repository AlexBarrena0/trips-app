package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.converter.TripConverter;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.domain.repository.TripRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.TripMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TripRepositoryImpl implements TripRepository {


    private final TripMapper tripMapper;

    public TripRepositoryImpl(TripMapper tripMapper) {
        this.tripMapper = tripMapper;
    }

    @Override
    public Long save(Trip trip) {
        TripEntity tripEntity = TripConverter.INSTANCE.toEntity(trip);
        tripMapper.save(tripEntity);
        return tripEntity.getId();
    }

    @Override
    public Trip findById(Long id) {
        return TripConverter.INSTANCE.toDomain(tripMapper.findById(id));
    }

    @Override
    public List<Trip> search(Map<String, Object> params) {
        return TripConverter.INSTANCE.toDomain(tripMapper.search(params));
    }

    @Override
    public void update(Trip trip) {
        TripEntity tripEntity = TripConverter.INSTANCE.toEntity(trip);
        tripMapper.update(tripEntity);
    }
}
