package com.object_oriented_case.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.object_oriented_case.portfolio.dto.VehicleCreateRequest;
import com.object_oriented_case.portfolio.dto.VehicleUpdateRequest;
import com.object_oriented_case.portfolio.model.Mark;
import com.object_oriented_case.portfolio.model.Model;
import com.object_oriented_case.portfolio.model.Vehicle;
import com.object_oriented_case.portfolio.repository.VehicleRepository;

@Service
public class VehicleService {

    private final MarkService markService;
    private final ModelService modelService;
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository, MarkService markService, ModelService modelService) {
        this.vehicleRepository = vehicleRepository;
        this.markService = markService;
        this.modelService = modelService;
    }

    public List<Vehicle> getAllVehicles(String search) {
        if (search == null || search.isEmpty()) {
            return vehicleRepository.findAll();
        } else {
            return vehicleRepository.searchByAllFields(search);
        }

    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle createVehicle(VehicleCreateRequest vehicle) {
        Vehicle newVehicle = new Vehicle();

        newVehicle.setYear(vehicle.getYear());
        newVehicle.setColor(vehicle.getColor());
        newVehicle.setPrice(vehicle.getPrice());
        newVehicle.setMileage(vehicle.getMileage());
        newVehicle.setStatus(Vehicle.VehicleStatus.valueOf(vehicle.getStatus().name().toUpperCase()));

        Model model = modelService.getModelById(vehicle.getModelId());
        Mark mark = markService.getMarkById(vehicle.getMarkId());

        newVehicle.setModel(model);
        newVehicle.setMark(mark);

        return vehicleRepository.save(newVehicle);
    }

    public Vehicle updateVehicle(Long id, VehicleUpdateRequest vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);

        Mark mark = markService.getMarkById(vehicleDetails.getMarkId());
        Model model = modelService.getModelById(vehicleDetails.getModelId());

        vehicle.setMark(mark);
        vehicle.setModel(model);
        vehicle.setYear(vehicleDetails.getYear());
        vehicle.setPrice(vehicleDetails.getPrice());
        vehicle.setMileage(vehicleDetails.getMileage());
        vehicle.setColor(vehicleDetails.getColor());
        vehicle.setStatus(Vehicle.VehicleStatus.valueOf(vehicleDetails.getStatus().toUpperCase()));
        return vehicleRepository.save(vehicle);
    }

    public boolean deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
        return true;
    }
}
