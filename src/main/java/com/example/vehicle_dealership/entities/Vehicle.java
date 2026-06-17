package com.example.vehicle_dealership.entities;

import com.example.vehicle_dealership.entities.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Min(value = 1886,message = "Year must be at least 1886")
    @Max(value = 2028, message = "Year must be no more than 2028")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealership_id",nullable = false)
    @JsonIgnore
    private Dealerships dealership;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> contracts;
}

