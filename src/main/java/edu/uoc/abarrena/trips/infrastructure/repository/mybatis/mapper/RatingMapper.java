package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RatingMapper {

    @Insert("INSERT INTO RATING (SHIP, ROOM, CREW, FOOD, COMMENT, CRUISE_ID) VALUES (#{ship}, #{room}, #{crew}, #{food}, #{comment}, #{cruise.id})")
    void save(RatingEntity ratingEntity);

    @Results(id = "ratingResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "ship", column = "ship"),
        @Result(property = "room", column = "room"),
        @Result(property = "crew", column = "crew"),
        @Result(property = "food", column = "food"),
        @Result(property = "comment", column = "comment"),
        @Result(property = "cruise", column = "cruise_id", javaType = edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity.class,
            one = @One(select = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper.findById"))
    })
    @Select("SELECT * FROM RATING WHERE ID = #{id}")
    RatingEntity findById(Long id);

    @Select("SELECT * FROM RATING WHERE CRUISE_ID = #{id}")
    List<RatingEntity> findByCruiseId(Long id);

    @Delete("DELETE FROM RATING WHERE ID = #{id}")
    void delete(Long id);
}
