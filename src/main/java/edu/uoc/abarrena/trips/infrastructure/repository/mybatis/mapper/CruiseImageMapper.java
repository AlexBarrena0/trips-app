package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider.CruiseImageEntitySqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CruiseImageMapper {

    @InsertProvider(type = CruiseImageEntitySqlProvider.class, method = "save")
    public void save(HashMap<String, Object> params);

    @Select("SELECT IMAGE_ID FROM CRUISE_IMAGE WHERE CRUISE_ID = #{cruiseId}")
    public List<Long> findByCruiseId(Long cruiseId);

    @Delete("DELETE FROM CRUISE_IMAGE WHERE CRUISE_ID = #{cruiseId}")
    public void deleteByCruiseId(Long cruiseId);
}
