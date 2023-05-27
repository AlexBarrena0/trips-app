package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider;

import java.util.List;

public class CruiseImageEntitySqlProvider {

    public String save(Long cruiseId, List<Long> imageIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO CRUISE_IMAGE (CRUISE_ID, IMAGE_ID) VALUES ");
        for (int i = 0; i < imageIds.size(); i++) {
            sql.append("(#{cruiseId}, #{imageIds[" + i + "]})");
            if (i < imageIds.size() - 1) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }
}
