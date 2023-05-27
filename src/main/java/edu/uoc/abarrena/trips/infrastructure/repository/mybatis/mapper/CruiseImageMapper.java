package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider.CruiseImageEntitySqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CruiseImageMapper {

    @InsertProvider(type = CruiseImageEntitySqlProvider.class, method = "save")
    public void save(Long cruiseId, List<Long> imageId);

    @Select("SELECT IMAGE_ID FROM CRUISE_IMAGE WHERE CRUISE_ID = #{cruiseId}")
    public List<Long> findByCruiseId(Long cruiseId);

    @Delete("DELETE FROM CRUISE_IMAGE WHERE IMAGE_ID = #{imageId}")
    public void deleteByImageId(Long imageId);
}
