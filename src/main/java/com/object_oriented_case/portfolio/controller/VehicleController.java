package com.object_oriented_case.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.object_oriented_case.portfolio.dto.VehicleCreateRequest;
import com.object_oriented_case.portfolio.dto.VehicleUpdateRequest;
import com.object_oriented_case.portfolio.model.Vehicle;
import com.object_oriented_case.portfolio.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam(required = false) String search) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles(search);
        System.out.println("Search " + search);

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);

        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
            @RequestBody VehicleUpdateRequest vehicleDetails) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);

        if (updatedVehicle == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedVehicle);
    }

    @PostMapping("/create")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleCreateRequest vehicle) {
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        if (createdVehicle == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(createdVehicle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long id) {
        boolean isDeleted = vehicleService.deleteVehicle(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
