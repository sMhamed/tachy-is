package de.axone.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "plateNr")
    private String plateNr;

    // Field used in validation (form4)
    @Column(name = "type")
    private String type;

    @Column(name = "mileage")
    private String mileage;

    // Field used in validation (form4)
    @Column(name = "mecDate")
    private LocalDate mecDate;

    @Column(name = "chronoManufacturer")
    private String chronoManufacturer;

    @Column(name = "chronoPlateNr")
    private String chronoPlateNr;

    @Column(name = "chronoSerieNr")
    private String chronoSerieNr;

    @Column(name = "chronoHomologation")
    private String chronoHomologation;

    @Column(name = "chronoType")
    private String chronoType;

    @Column(name = "chronoVmax")
    private String chronoVmax;

    // Field used in validation (form4)
    @Column(name = "chronoContructionYear")
    private Integer chronoConstructionYear;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "vehicle_id")
//    private List<ServiceOrder> serviceOrders;
}
