package com.example.vehicle_dealership.entities;

import com.example.vehicle_dealership.entities.enums.ContractType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Date is Required")
    @Column(nullable = false,length = 10)
    private String date;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType type;

    @NotBlank(message = "Customer Name is Required")
    @Column(nullable = false, length = 1000)
    private String customerName;

    @Column(nullable = true, length = 100)
    private String customerEmail;

    @NotBlank(message = "Customer Phone Number is Required")
    @Size(min = 12,max = 18, message = "Phone Numbers require between 12 and 18 characters")
    @Column(nullable = false,length = 18)
    private String customerNumber;

}
