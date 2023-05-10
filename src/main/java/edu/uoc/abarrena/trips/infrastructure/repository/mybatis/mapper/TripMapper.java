package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider.TripEntitySqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TripMapper {

    @Insert("INSERT INTO TRIP (ROUTE, START_DATE, END_DATE, N_DIVES, PRICE, CRUISE_ID, DESTINATION_ID) VALUES (#{route}, #{startDate}, #{endDate}, #{nDives}, #{price}, #{cruise.id}, #{destination.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void save(TripEntity tripEntity);

    @Results(id = "tripResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "route", column = "route"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "nDives", column = "n_dives"),
            @Result(property = "price", column = "price"),
            @Result(property = "cruise", column = "cruise_id", one = @One(select = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper.findById")),
            @Result(property = "destination", column = "destination_id", one = @One(select = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper.findById"))
    })
    @Select("SELECT * FROM TRIP " +
            "JOIN CRUISE ON TRIP.CRUISE_ID = CRUISE.ID " +
            "JOIN DESTINATION ON TRIP.DESTINATION_ID = DESTINATION.ID " +
            "WHERE TRIP.ID = #{id}")
    public TripEntity findById(Long id);

    @ResultMap("tripResult")
    @SelectProvider(type = TripEntitySqlProvider.class, method = "search")
    public List<TripEntity> search(Map<String, Object> params);

    @UpdateProvider(type = TripEntitySqlProvider.class, method = "update")
    public void update(TripEntity tripEntity);
}
