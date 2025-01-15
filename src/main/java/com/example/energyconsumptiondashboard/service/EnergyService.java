package com.example.energyconsumptiondashboard.service;

import com.example.energyconsumptiondashboard.model.EnergyUsage;
import com.example.energyconsumptiondashboard.repository.EnergyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyService {

    private final EnergyUsageRepository energyUsageRepository;

    @Autowired
    public EnergyService(EnergyUsageRepository energyUsageRepository) {
        this.energyUsageRepository = energyUsageRepository;
    }

    /**
     * Method to track energy usage and save it to the database.
     *
     * @param deviceName       the name of the device (e.g., "Air Conditioner")
     * @param powerConsumption the energy consumed by the device (in watts)
     */
    public void trackEnergyUsage(String deviceName, double powerConsumption) {
        long timestamp = System.currentTimeMillis();
        EnergyUsage energyUsage = new EnergyUsage("1", deviceName, powerConsumption, timestamp);

        // Save the energy usage record to InfluxDB
        energyUsageRepository.save(energyUsage);
    }

    /**
     * Retrieve all energy usage records from the database.
     *
     * @return a list of all EnergyUsage records
     */
    public List<EnergyUsage> getAllEnergyUsage() {
        return energyUsageRepository.findAll();
    }

    /**
     * Retrieve energy usage records within a specific time range.
     *
     * @param startTime the start time of the range (in milliseconds)
     * @param endTime   the end time of the range (in milliseconds)
     * @return a list of EnergyUsage records within the time range
     */
    public List<EnergyUsage> getEnergyUsageWithinTimeRange(long startTime, long endTime) {
        return energyUsageRepository.findByTimeRange(startTime, endTime);
    }

    /**
     * Delete all energy usage records from the database.
     * Use with caution as this will remove all data.
     */
    public void deleteAllEnergyUsage() {
        energyUsageRepository.deleteAll();
    }

    /**
     * Method to calculate the total energy consumption over a period of time.
     *
     * @return the total energy consumption in watts
     */
    public double calculateTotalEnergyConsumption() {
        List<EnergyUsage> allEnergyUsage = getAllEnergyUsage();
        return allEnergyUsage.stream()
                .mapToDouble(EnergyUsage::getPowerConsumption)
                .sum();
    }

    /**
     * Method to get the energy usage statistics, like average, min, max.
     *
     * @return a string summarizing the energy usage statistics
     */
    public String getEnergyUsageStatistics() {
        List<EnergyUsage> allEnergyUsage = getAllEnergyUsage();
        if (allEnergyUsage.isEmpty()) {
            return "No energy usage data available.";
        }

        double totalEnergyConsumption = allEnergyUsage.stream()
                .mapToDouble(EnergyUsage::getPowerConsumption)
                .sum();
        double averageEnergyConsumption = totalEnergyConsumption / allEnergyUsage.size();
        double maxEnergyConsumption = allEnergyUsage.stream()
                .mapToDouble(EnergyUsage::getPowerConsumption)
                .max()
                .orElse(0.0);
        double minEnergyConsumption = allEnergyUsage.stream()
                .mapToDouble(EnergyUsage::getPowerConsumption)
                .min()
                .orElse(0.0);

        return String.format("Total Consumption: %.2fW, Average Consumption: %.2fW, Max Consumption: %.2fW, Min Consumption: %.2fW",
                totalEnergyConsumption, averageEnergyConsumption, maxEnergyConsumption, minEnergyConsumption);
    }
}

