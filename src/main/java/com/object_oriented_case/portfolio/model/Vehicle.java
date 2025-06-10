package com.object_oriented_case.portfolio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private Long year;
    private String color;
    private Double price;
    private String mileage;
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "markId", referencedColumnName = "markId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "modelId", referencedColumnName = "modelId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Model model;

    public enum VehicleStatus {
        AVAILABLE, SOLD, RESERVED
    }

}
