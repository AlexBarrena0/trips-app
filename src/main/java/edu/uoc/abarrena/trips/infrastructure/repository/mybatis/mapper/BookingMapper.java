package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.BookingEntity;
import org.apache.ibatis.annotations.*;


@Mapper
public interface BookingMapper {

    @Insert("INSERT INTO BOOKING (STATUS, TRIP_ID, TRAVELER_ID) VALUES (#{status}, #{trip.id}, #{travelerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(BookingEntity bookingEntity);

    @Results(id = "bookingResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "status", column = "status"),
            @Result(property = "travelerId", column = "traveler_id"),
            @Result(property = "trip", column = "trip_id", one = @One(select = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.TripMapper.findById"))
    })
    @Select("SELECT * FROM BOOKING WHERE ID = #{id}")
    BookingEntity findById(Long id);

    @Update("UPDATE BOOKING SET STATUS = #{status} WHERE ID = #{id}")
    void update(BookingEntity bookingEntity);
}
