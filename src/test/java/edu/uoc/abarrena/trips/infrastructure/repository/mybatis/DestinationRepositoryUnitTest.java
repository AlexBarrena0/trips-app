package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DestinationRepositoryUnitTest extends BaseTest {

    @Mock
    private DestinationMapper destinationMapper;

    @InjectMocks
    private DestinationRepositoryImpl destinationRepository;

    @Test
    public void save_saveDestination() {
        Long expectedId = 1L;

        Destination destination = new Destination(expectedId, "Maldives");

        DestinationEntity destinationEntity = new DestinationEntity(expectedId, "Maldives");
        doNothing().when(destinationMapper).save(destinationEntity);

        Long actualId = destinationRepository.save(destination);

        assertEquals(expectedId, actualId);
        verify(destinationMapper).save(destinationEntity);
    }

    @Test
    public void findById_returnDestination() {
        Long id = 1L;
        DestinationEntity destinationEntity = new DestinationEntity(id, "Maldives");
        when(destinationMapper.findById(id)).thenReturn(destinationEntity);

        Destination actualDestination = destinationRepository.findById(id);

        Destination expectedDestination = new Destination(id, "Maldives");
        assertEquals(expectedDestination, actualDestination);
        verify(destinationMapper).findById(id);
    }

    @Test
    public void findByName_returnDestination() {
        String name = "New York";
        DestinationEntity destinationEntity = new DestinationEntity(1L, name);
        when(destinationMapper.findByDescription(name)).thenReturn(destinationEntity);

        Destination actualDestination = destinationRepository.findByName(name);

        Destination expectedDestination = new Destination(1L, name);
        assertEquals(expectedDestination, actualDestination);
        verify(destinationMapper).findByDescription(name);
    }

    @Test
    public void findAll_returnDestinations() {
        List<DestinationEntity> destinationEntities = Arrays.asList(
                new DestinationEntity(1L, "Maldives"),
                new DestinationEntity(2L, "Red Sea")
        );
        when(destinationMapper.findAll()).thenReturn(destinationEntities);

        List<Destination> actualDestinations = destinationRepository.findAll();

        List<Destination> expectedDestinations = Arrays.asList(
                new Destination(1L, "Maldives"),
                new Destination(2L, "Red Sea")
        );
        assertEquals(expectedDestinations, actualDestinations);
        verify(destinationMapper).findAll();
    }
}
