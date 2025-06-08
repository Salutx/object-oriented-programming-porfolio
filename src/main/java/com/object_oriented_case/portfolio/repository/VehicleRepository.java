package com.object_oriented_case.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.object_oriented_case.portfolio.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("""
                SELECT v FROM Vehicle v
                JOIN v.mark m
                JOIN v.model mo
                WHERE
                    CAST(v.year AS string) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(v.color) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR CAST(v.price AS string) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(v.mileage) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(CAST(v.status AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%'))
                 OR LOWER(mo.name) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    List<Vehicle> searchByAllFields(@Param("search") String search);

}
