package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.application.converter.DestinationConverter;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    private final DestinationMapper destinationMapper;

    public DestinationRepositoryImpl(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }
    @Override
    public Long save(Destination destination) {
        DestinationEntity destinationEntity = DestinationConverter.INSTANCE.toEntity(destination);
        destinationMapper.save(destinationEntity);
        return destinationEntity.getId();
    }

    @Override
    public Destination findById(Long id) {
        return DestinationConverter.INSTANCE.toDomain(destinationMapper.findById(id));
    }

    @Override
    public Destination findByName(String name) {
        return DestinationConverter.INSTANCE.toDomain(destinationMapper.findByDescription(name));
    }

    @Override
    public List<Destination> findAll() {
        return DestinationConverter.INSTANCE.toDomain(destinationMapper.findAll());
    }
}
