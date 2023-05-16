package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CruiseMapper {

    @Insert("INSERT INTO CRUISE (NAME, DESCRIPTION, CAPACITY) VALUES (#{name}, #{description}, #{capacity})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(CruiseEntity cruiseEntity);

    @Results(id = "cruiseResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "capacity", column = "capacity"),
            @Result(property = "avgShipRating", column = "avg_ship_rating"),
            @Result(property = "avgRoomRating", column = "avg_room_rating"),
            @Result(property = "avgCrewRating", column = "avg_crew_rating"),
            @Result(property = "avgFoodRating", column = "avg_food_rating"),
            @Result(property = "ratings", column = "id", many = @Many(select = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.RatingMapper.findByCruiseId"))
    })
    @Select("SELECT * FROM CRUISE " +
            "JOIN RATING ON RATING.CRUISE_ID = CRUISE.ID " +
            "WHERE CRUISE.ID = #{id}")
    CruiseEntity findById(Long id);

    @Update("UPDATE CRUISE SET NAME = #{name}, DESCRIPTION = #{description}, CAPACITY = #{capacity} WHERE ID = #{id}")
    void update(CruiseEntity cruiseEntity);

    @Delete("DELETE FROM CRUISE WHERE ID = #{id}")
    void delete(Long id);
}
