package com.example.energyconsumptiondashboard.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

    @Value("${influxdb.url}")
    private String influxDbUrl;

    @Value("${influxdb.database}")
    private String influxDbDatabase;

    @Value("${influxdb.username}")
    private String influxDbUsername;

    @Value("${influxdb.password}")
    private String influxDbPassword;

    /**
     * Bean to create InfluxDB connection.
     * @return InfluxDB instance
     */
    @Bean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxDbUrl, influxDbUsername, influxDbPassword);

        // Create or switch to the specified database
        influxDB.setDatabase(influxDbDatabase);

        // Optional: Create retention policy for the database (if needed)
        // influxDB.query(new Query("CREATE RETENTION POLICY \"autogen\" ON \"mydb\" DURATION 30d REPLICATION 1 DEFAULT", influxDbDatabase));

        return influxDB;
    }

    /**
     * Utility method to write a point to InfluxDB (example usage).
     * @param measurement the measurement name
     * @param field the field name
     * @param value the value to store in the field
     */
    public void writeData(String measurement, String field, Object value) {
        InfluxDB influxDB = influxDB();

        Point point = Point.measurement(measurement)
                .time(System.currentTimeMillis(), java.util.concurrent.TimeUnit.MILLISECONDS)
                .addField(field, value.toString())
                .build();

        influxDB.write(point);
    }
}

