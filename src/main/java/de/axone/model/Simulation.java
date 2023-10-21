package de.axone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "simulation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "errorPerSecond")
    private String errorPerSecond;
    @Column(name = "errorPerMinute")
    private String errorPerMinute;
    @Column(columnDefinition = "SMALLINT", name = "isComplaint")
    private Boolean isComplaint;
    @Column(columnDefinition = "SMALLINT", name = "isSpeedComplaint")
    private Boolean isSpeedComplaint;
    @Column(columnDefinition = "SMALLINT", name = "isDistanceComplaint")
    private Boolean isDistanceComplaint;
    @Column(columnDefinition = "SMALLINT", name = "isTracingComplaint")
    private Boolean isTracingComplaint;


}
