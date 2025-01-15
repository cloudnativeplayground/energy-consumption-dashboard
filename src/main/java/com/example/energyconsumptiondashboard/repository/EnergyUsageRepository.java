package com.example.energyconsumptiondashboard.repository;

import com.example.energyconsumptiondashboard.model.EnergyUsage;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EnergyUsageRepository {

    private final InfluxDB influxDB;
    private final String databaseName;

    @Autowired
    public EnergyUsageRepository(InfluxDB influxDB) {
        this.influxDB = influxDB;
        this.databaseName = "energy_usage_db"; // You can also make this configurable in application.properties
    }

    /**
     * Write EnergyUsage data to InfluxDB.
     *
     * @param energyUsage the energy usage record to be written
     */
    public void save(EnergyUsage energyUsage) {
        influxDB.write(energyUsage.toInfluxDBPoint());
    }

    /**
     * Retrieve EnergyUsage data from InfluxDB based on a query.
     * This example assumes you want to query for all energy usage data.
     *
     * @return List of EnergyUsage records
     */
    public List<EnergyUsage> findAll() {
        String queryStr = "SELECT * FROM energy_usage";
        Query query = new Query(queryStr, databaseName);

        // Execute the query and get the result
        List<EnergyUsage> energyUsageList = influxDB.query(query).getResults()
                .stream()
                .flatMap(result -> result.getSeries().stream())
                .flatMap(series -> series.getValues().stream())
                .map(values -> {
                    // Assuming the first column is the timestamp, second is the device name, and third is the power consumption
                    String deviceName = (String) values.get(1);
                    Double powerConsumption = Double.valueOf((String) values.get(2));
                    long timestamp = Long.valueOf((String) values.get(0));
                    return new EnergyUsage("1", deviceName, powerConsumption, timestamp);  // Construct the EnergyUsage object
                })
                .collect(Collectors.toList());

        return energyUsageList;
    }

    /**
     * Query EnergyUsage data within a time range from InfluxDB.
     * Example: Retrieving records within a given time window.
     *
     * @param startTime the start timestamp for the query
     * @param endTime the end timestamp for the query
     * @return List of EnergyUsage records within the specified time range
     */
    public List<EnergyUsage> findByTimeRange(long startTime, long endTime) {
        String queryStr = String.format("SELECT * FROM energy_usage WHERE time >= %dms AND time <= %dms", startTime, endTime);
        Query query = new Query(queryStr, databaseName);

        // Execute the query and get the result
        List<EnergyUsage> energyUsageList = influxDB.query(query).getResults()
                .stream()
                .flatMap(result -> result.getSeries().stream())
                .flatMap(series -> series.getValues().stream())
                .map(values -> {
                    // Assuming the first column is the timestamp, second is the device name, and third is the power consumption
                    String deviceName = (String) values.get(1);
                    Double powerConsumption = Double.valueOf((String) values.get(2));
                    long timestamp = Long.valueOf((String) values.get(0));
                    return new EnergyUsage("1", deviceName, powerConsumption, timestamp);  // Construct the EnergyUsage object
                })
                .collect(Collectors.toList());

        return energyUsageList;
    }

    /**
     * Delete all data from the energy_usage measurement (use with caution).
     */
    public void deleteAll() {
        String queryStr = "DELETE FROM energy_usage";
        Query query = new Query(queryStr, databaseName);
        influxDB.query(query);
    }

}
