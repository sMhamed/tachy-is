package de.axone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "final_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "measuredDistance")
    private Double measuredDistance;

    @Column(name = "travelledDistance")
    private Double travelledDistance;

    @Column(name = "error")
    private Double error;

    @Column(name = "isComplaint", columnDefinition = "SMALLINT")
    private Boolean isComplaint;

    @Column(name = "isSealingAndMarkingComplaint", columnDefinition = "SMALLINT")
    private Boolean isSealingAndMarkingComplaint;

    @Column(name = "isPenalty", columnDefinition = "SMALLINT")
    private Boolean isPenalty;

    @Column(name = "isTachographComplaint", columnDefinition = "SMALLINT")
    private Boolean isTachographComplaint;
}
