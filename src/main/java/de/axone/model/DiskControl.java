package de.axone.model;

import de.axone.enums.ControlType;
import de.axone.enums.DiskControlType;
import de.axone.enums.MeasurementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disk_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiskControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "control")
    @Enumerated(EnumType.STRING)
    private DiskControlType control;

    @Column(name = "controlType")
    @Enumerated(EnumType.STRING)
    private ControlType controlType;

    @Column(name = "measurementType")
    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;

    @Column(name = "isComplaint", columnDefinition = "SMALLINT")
    private Boolean isComplaint;
}
