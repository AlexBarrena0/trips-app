package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider.TripEntitySqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TripMapper {

    @Insert("INSERT INTO TRIP (ID, ROUTE, START_DATE, END_DATE, N_DIVES, PRICE, CRUISE_ID, DESTINATION_ID) VALUES (#{id}, #{route}, #{startDate}, #{endDate}, #{nDives}, #{price}, #{cruise.id}, #{destination.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void save(TripEntity tripEntity);

    @Select("SELECT * FROM TRIP WHERE ID = #{id} " +
            "JOIN CRUISE ON TRIP.CRUISE_ID = CRUISE.ID" +
            "JOIN DESTINATION ON TRIP.DESTINATION_ID = DESTINATION.ID")
    public TripEntity findById(Long id);

    @SelectProvider(type = TripEntitySqlProvider.class, method = "search")
    public List<TripEntity> search(Map<String, Object> params);

    @UpdateProvider(type = TripEntitySqlProvider.class, method = "update")
    public void update(TripEntity tripEntity);
}
