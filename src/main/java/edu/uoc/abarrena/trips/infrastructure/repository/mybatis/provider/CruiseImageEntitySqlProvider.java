package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider;

import java.util.HashMap;
import java.util.List;

public class CruiseImageEntitySqlProvider {

    public String save(HashMap<String, Object> params) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO CRUISE_IMAGE (CRUISE_ID, IMAGE_ID) VALUES ");
        for (int i = 0; i < ((List<Long>)params.get("imagesIds")).size(); i++) {
            sql.append("(#{cruiseId}, #{imagesIds[" + i + "]})");
            if (i < ((List<Long>)params.get("imagesIds")).size() - 1) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }
}
