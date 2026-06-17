package com.example.vehicle_dealership.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dealership")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dealerships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    @Size(min = 1, max = 200, message = "Dealership Name must be between 1 and 200 characters")
    @Column(nullable = false,length = 200)
    private String name;

    @NotBlank(message = "Address is Required")
    @Column(nullable = false, length = 100)
    private String address;

    @NotBlank(message = "Phone Number is Required")
    @Size(min = 12,max = 18, message = "Phone Numbers require between 12 and 18 characters")
    @Column(nullable = false,length = 18)
    private String phone;

    @OneToMany(mappedBy = "dealership",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Vehicle> inventory;
}
