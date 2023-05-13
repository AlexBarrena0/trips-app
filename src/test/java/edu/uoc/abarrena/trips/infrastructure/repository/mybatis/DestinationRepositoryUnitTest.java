package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.factory.DestinationFactory;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DestinationRepositoryUnitTest extends BaseUnitTest {

    @Mock
    private DestinationMapper destinationMapper;

    @InjectMocks
    private DestinationRepositoryImpl destinationRepository;

    @Test
    void save_saveDestination() {
        Long expectedId = 1L;
        Destination destination = DestinationFactory.destinationDomain(expectedId);

        DestinationEntity destinationEntity = DestinationFactory.destinationEntity(expectedId);
        doNothing().when(destinationMapper).save(destinationEntity);

        Long actualId = destinationRepository.save(destination);

        assertEquals(expectedId, actualId);
    }

    @Test
    void findById_returnDestination() {
        Long id = 1L;
        DestinationEntity destinationEntity = DestinationFactory.destinationEntity(id);
        when(destinationMapper.findById(id)).thenReturn(destinationEntity);

        Destination actualDestination = destinationRepository.findById(id);

        Destination expectedDestination = DestinationFactory.destinationDomain(id);
        assertEquals(expectedDestination, actualDestination);
    }

    @Test
    void findByName_returnDestination() {
        String name = "Maldives";
        Long id = 1L;
        DestinationEntity destinationEntity = DestinationFactory.destinationEntity(id);
        when(destinationMapper.findByDescription(name)).thenReturn(destinationEntity);

        Destination actualDestination = destinationRepository.findByName(name);

        Destination expectedDestination = DestinationFactory.destinationDomain(id);
        assertEquals(expectedDestination, actualDestination);
    }

    @Test
    void findAll_returnDestinations() {
        List<DestinationEntity> destinationEntities = DestinationFactory.destinationListEntity();
        when(destinationMapper.findAll()).thenReturn(destinationEntities);

        List<Destination> actualDestinations = destinationRepository.findAll();

        List<Destination> expectedDestinations = DestinationFactory.destinationListDomain();
        assertEquals(expectedDestinations, actualDestinations);
    }
}
