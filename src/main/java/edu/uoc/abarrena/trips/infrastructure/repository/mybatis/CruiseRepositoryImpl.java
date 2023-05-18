package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.converter.CruiseConverter;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CruiseRepositoryImpl implements CruiseRepository {

    private final CruiseMapper cruiseMapper;

    public CruiseRepositoryImpl(CruiseMapper cruiseMapper) {
        this.cruiseMapper = cruiseMapper;
    }

    @Override
    public Long save(Cruise cruise) {
        CruiseEntity cruiseEntity = CruiseConverter.INSTANCE.toEntity(cruise);
        cruiseMapper.save(cruiseEntity);
        return cruiseEntity.getId();
    }

    @Override
    public Cruise findById(Long id) {
        return CruiseConverter.INSTANCE.toDomain(cruiseMapper.findById(id));
    }

    @Override
    public void update(Cruise cruise) {
        CruiseEntity cruiseEntity = CruiseConverter.INSTANCE.toEntity(cruise);
        cruiseMapper.update(cruiseEntity);
    }
}
