package com.example.vehicle_dealership.entities;

import com.example.vehicle_dealership.entities.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vin is Required")
    @Column(nullable = false, length = 17)
    private String vin;

    @NotBlank(message = "Year is required")
    @Size(min = 1886, max = 2028, message = "Year must be between 1886 and 2028")
    @Column(nullable = false, length = 4)
    private int year;

    @NotBlank(message = "Make is required")
    @Column(nullable = false, length = 100)
    private String make;

    @NotBlank(message = "Model is required")
    @Column(nullable = false, length = 100)
    private String model;

    @NotBlank(message = "Color is required")
    @Column(nullable = false, length = 100)
    private String color;

    @NotNull(message = "Vehicle Type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Positive(message = "Odometer must be a positive number")
    @Column(nullable = false,length = 10)
    private int miles;

    @Positive(message = "Price must be a positive number")
    @Column(nullable = false, length = 10)
    private double price;

    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealerships dealership;

    @OneToMany(mappedBy = "vehicle")
    private List<Contract> contracts;
}

