package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CruiseMapper {

    @Insert("INSERT INTO CRUISE (NAME, DESCRIPTION, CAPACITY) VALUES (#{name}, #{description}, #{capacity})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(CruiseEntity cruiseEntity);

    @Select("SELECT * FROM CRUISE WHERE ID = #{id}")
    CruiseEntity findById(Long id);

    @Update("UPDATE CRUISE SET NAME = #{name}, DESCRIPTION = #{description}, CAPACITY = #{capacity} WHERE ID = #{id}")
    void update(CruiseEntity cruiseEntity);
}
