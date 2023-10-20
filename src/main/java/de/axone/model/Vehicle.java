package de.axone.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String vin;

    @Column
    private String manufacturer;

    @Column
    private String plateNr;

    @Column
    private String mileage;

    @Column
    private String chronoManufacturer;

    @Column
    private String chronoPlateNr;

    @Column
    private String chronoSerieNr;

    @Column
    private String chronoHomologation;

    @Column
    private String chronoVmax;

    @Column(columnDefinition = "SMALLINT")
    private Boolean confirmation1;

    @Column(columnDefinition = "SMALLINT")
    private Boolean confirmation2;

    @Column(columnDefinition = "SMALLINT")
    private Boolean confirmation3;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tachy_order_id")
    private TachyOrder tachyOrder;
}
