package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.factory.TripFactory;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.TripMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TripRepositoryUnitTest extends BaseTest {

    @Mock
    private TripMapper tripMapper;

    @InjectMocks
    private TripRepositoryImpl tripRepository;

    @Test
    void save_saveTrip() {
        Long expectedId = 1L;
        Trip trip = TripFactory.tripDomain(expectedId);

        TripEntity tripEntity = TripFactory.tripEntity(expectedId);
        doNothing().when(tripMapper).save(tripEntity);

        Long actualId = tripRepository.save(trip);

        assertEquals(expectedId, actualId);
    }

    @Test
    void findById_returnTrip() {
        Long id = 1L;
        TripEntity tripEntity = TripFactory.tripEntity(id);
        when(tripMapper.findById(id)).thenReturn(tripEntity);

        Trip actualTrip = tripRepository.findById(id);

        Trip expectedTrip = TripFactory.tripDomain(id);
        assertEquals(expectedTrip, actualTrip);
    }
    @Test
    void search_returnTrip() {
        Long id = 1L;
        TripEntity tripEntity = TripFactory.tripEntity(id);
        when(tripMapper.search(any())).thenReturn(List.of(tripEntity));

        List<Trip> searchResult = tripRepository.search(null);

        Trip expectedTrip = TripFactory.tripDomain(id);
        assertEquals(1, searchResult.size());
        assertEquals(expectedTrip, searchResult.get(0));
    }

    @Test
    void update_updateTrip() {
        Long id = 1L;
        Trip trip = TripFactory.tripDomain(id);

        TripEntity tripEntity = TripFactory.tripEntity(id);
        doNothing().when(tripMapper).update(tripEntity);

        tripRepository.update(trip);

    }
}
