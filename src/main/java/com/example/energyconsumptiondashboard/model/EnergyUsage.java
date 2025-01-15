package com.example.energyconsumptiondashboard.model;

import java.util.concurrent.TimeUnit;

public class EnergyUsage {

    private String id;
    private String deviceName;
    private double powerConsumption;
    private long timestamp;

    // Default constructor
    public EnergyUsage() {
    }

    // Constructor with all fields
    public EnergyUsage(String id, String deviceName, double powerConsumption, long timestamp) {
        this.id = id;
        this.deviceName = deviceName;
        this.powerConsumption = powerConsumption;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Method to convert the timestamp to a human-readable format (optional)
    public String getFormattedTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date(this.timestamp));
    }

    @Override
    public String toString() {
        return "EnergyUsage{" +
                "id='" + id + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", powerConsumption=" + powerConsumption +
                ", timestamp=" + getFormattedTimestamp() +
                '}';
    }

    /**
     * Helper method to create a point for InfluxDB from this object.
     * This can be used to write data to InfluxDB.
     *
     * @return Point to be written to InfluxDB
     */
    public org.influxdb.dto.Point toInfluxDBPoint() {
        return org.influxdb.dto.Point.measurement("energy_usage")
                .time(this.timestamp, TimeUnit.MILLISECONDS)
                .addField("device_name", this.deviceName)
                .addField("power_consumption", this.powerConsumption)
                .build();
    }
}

