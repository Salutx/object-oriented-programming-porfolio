package com.object_oriented_case.portfolio.specification;

import com.object_oriented_case.portfolio.dto.VehicleSearchRequest;
import com.object_oriented_case.portfolio.model.Vehicle;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class VehicleSpecification {

    public static Specification<Vehicle> search(VehicleSearchRequest request) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            if (request.getMark() != null && !request.getMark().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("mark")), "%" + request.getMark().toLowerCase() + "%"));
            }

            if (request.getModel() != null && !request.getModel().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("model")), "%" + request.getModel().toLowerCase() + "%"));
            }

            if (request.getYear() != null) {
                predicates.add(cb.equal(root.get("year"), request.getYear()));
            }

            if (request.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), request.getMinPrice()));
            }

            if (request.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), request.getMaxPrice()));
            }

            if (request.getMinMileage() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("mileage"), request.getMinMileage()));
            }

            if (request.getMaxMileage() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("mileage"), request.getMaxMileage()));
            }

            if (request.getColor() != null && !request.getColor().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("color")), request.getColor().toLowerCase()));
            }

            if (request.getStatus() != null && !request.getStatus().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("status")), request.getStatus().toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
