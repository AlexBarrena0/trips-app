package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TripResultMap {

    @ResultMap("tripResultMap")
    public Map<String, Object> tripResultMap() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", "id");
        resultMap.put("route", "route");
        resultMap.put("startDate", "startDate");
        resultMap.put("endDate", "endDate");
        resultMap.put("nDives", "nDives");
        resultMap.put("price", "price");
        resultMap.put("cruise.id", "cruise_id");
        resultMap.put("destination.id", "destination_id");
        return resultMap;
    }
}
