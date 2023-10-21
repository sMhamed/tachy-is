package de.axone.model;

import de.axone.enums.ControlType;
import de.axone.enums.ValueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "control_decision")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ControlAndDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "controlType")
    @Enumerated(EnumType.STRING)
    private ControlType controlType;

    @Column(name = "valueType")
    @Enumerated(EnumType.STRING)
    private ValueType valueType;

    @Column(name = "emt")
    private String emt;

    @Column(name = "vai")
    private String vai;

    @Column(name = "vp")
    private String vp;

    @Column(name = "isComplaint", columnDefinition = "SMALLINT")
    private Boolean isComplaint;
}
