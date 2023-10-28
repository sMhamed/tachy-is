package de.axone.model;

import de.axone.enums.SheetType;
import de.axone.enums.TireType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tire")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private SheetType name;

    @Column(name = "tire_type")
    @Enumerated(EnumType.STRING)
    private TireType tireType;

    @Column(name = "dimension")
    private String dimension;

    @Column(name = "brand")
    private String brand;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "indicator1")
    private Double indicator1;

    @Column(name = "indicator2")
    private Double indicator2;

    @Column(name = "indicator3")
    private Double indicator3;

    @Column(name = "indicator4")
    private Double indicator4;

}