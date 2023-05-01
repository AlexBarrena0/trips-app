package edu.uoc.abarrena.trips.infrastructure.repository.mybatis.provider;

import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;

import java.util.Map;

public class TripEntitySqlProvider {

    public String search(Map<String, Object> params) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TRIP " +
                "JOIN CRUISE ON TRIP.CRUISE_ID = CRUISE.ID " +
                "JOIN DESTINATION ON TRIP.DESTINATION_ID = DESTINATION.ID " +
                "WHERE 1=1 ");

        if (params.get("cruiseId") != null) {
            sql.append("AND TRIP.CRUISE_ID = #{cruiseId} ");
        }
        if (params.get("destinationId") != null) {
            sql.append("AND TRIP.DESTINATION_ID = #{destinationId} ");
        }
        if (params.get("startDate") != null) {
            sql.append("AND START_DATE >= #{startDate} ");
        }
        if (params.get("endDate") != null) {
            sql.append("AND END_DATE <= #{endDate} ");
        }
        return sql.toString();
    }

    public String update(TripEntity tripEntity) {
        Long id = tripEntity.getId();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE TRIP SET ");

        if (tripEntity.getRoute() != null) {
            sql.append("ROUTE = #{route}, ");
        }
        if (tripEntity.getStartDate() != null) {
            sql.append("START_DATE = #{startDate}, ");
        }
        if (tripEntity.getEndDate() != null) {
            sql.append("END_DATE = #{endDate}, ");
        }
        if (tripEntity.getNDives() != null) {
            sql.append("N_DIVES = #{nDives}, ");
        }
        if (tripEntity.getPrice() != null) {
            sql.append("PRICE = #{price}, ");
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE ID = #{id}");
        return sql.toString();
    }
}
