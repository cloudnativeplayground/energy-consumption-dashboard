package com.example.energyconsumptiondashboard.controller;

import com.example.energyconsumptiondashboard.model.EnergyUsage;
import com.example.energyconsumptiondashboard.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy-usage")
public class EnergyUsageController {

    private final EnergyService energyService;

    @Autowired
    public EnergyUsageController(EnergyService energyService) {
        this.energyService = energyService;
    }

    @GetMapping("/")
    public String getEnergyUsage() {
        return "Energy Usage Data";
    }
    /**
     * Endpoint to track energy usage data for a device.
     *
     * @param deviceName       the name of the device
     * @param powerConsumption the power consumed by the device
     * @return a response indicating success
     */
    @PostMapping("/track")
    public ResponseEntity<String> trackEnergyUsage(
            @RequestParam String deviceName,
            @RequestParam double powerConsumption) {
        try {
            energyService.trackEnergyUsage(deviceName, powerConsumption);
            return ResponseEntity.status(HttpStatus.CREATED).body("Energy usage data tracked successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error tracking energy usage: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve all energy usage records.
     *
     * @return a list of all energy usage records
     */
    @GetMapping("/all")
    public ResponseEntity<List<EnergyUsage>> getAllEnergyUsage() {
        try {
            List<EnergyUsage> energyUsageList = energyService.getAllEnergyUsage();
            if (energyUsageList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(energyUsageList);
            }
            return ResponseEntity.ok(energyUsageList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Endpoint to retrieve energy usage records within a time range.
     *
     * @param startTime the start time in milliseconds
     * @param endTime   the end time in milliseconds
     * @return a list of energy usage records within the specified time range
     */
    @GetMapping("/range")
    public ResponseEntity<List<EnergyUsage>> getEnergyUsageWithinTimeRange(
            @RequestParam long startTime,
            @RequestParam long endTime) {
        try {
            List<EnergyUsage> energyUsageList = energyService.getEnergyUsageWithinTimeRange(startTime, endTime);
            if (energyUsageList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(energyUsageList);
            }
            return ResponseEntity.ok(energyUsageList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Endpoint to delete all energy usage records.
     *
     * @return a response indicating success
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllEnergyUsage() {
        try {
            energyService.deleteAllEnergyUsage();
            return ResponseEntity.status(HttpStatus.OK).body("All energy usage data deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting energy usage data: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get the energy usage statistics (total, average, max, min).
     *
     * @return a string containing the energy usage statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<String> getEnergyUsageStatistics() {
        try {
            String statistics = energyService.getEnergyUsageStatistics();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving energy usage statistics: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get the total energy consumption.
     *
     * @return the total energy consumption in watts
     */
    @GetMapping("/total-consumption")
    public ResponseEntity<Double> calculateTotalEnergyConsumption() {
        try {
            double totalConsumption = energyService.calculateTotalEnergyConsumption();
            return ResponseEntity.ok(totalConsumption);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

