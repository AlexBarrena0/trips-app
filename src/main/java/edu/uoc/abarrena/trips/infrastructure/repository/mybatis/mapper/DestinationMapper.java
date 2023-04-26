package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DestinationMapper {

    @Insert("INSERT INTO DESTINATION (DESCRIPTION) VALUES (#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(DestinationEntity destinationEntity);

    @Select("SELECT * FROM DESTINATION WHERE ID = #{id}")
    DestinationEntity findById(Long id);

    @Select("SELECT * FROM DESTINATION WHERE DESCRIPTION = #{description}")
    DestinationEntity findByDescription(String description);

    @Select("SELECT * FROM DESTINATION")
    List<DestinationEntity> findAll();

}
