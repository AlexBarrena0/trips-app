package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RatingMapper {

    @Insert("INSERT INTO RATING (SHIP, ROOM, CREW, FOOD, COMMENT, CRUISE_ID) VALUES (#{ship}, #{room}, #{crew}, #{food}, #{comment}, #{cruise.id})")
    void save(RatingEntity ratingEntity);

    @Select("SELECT * FROM RATING WHERE CRUISE_ID = #{id}")
    List<RatingEntity> findByCruiseId(Long id);

    @Delete("DELETE FROM RATING WHERE ID = #{id}")
    void delete(Long id);
}
